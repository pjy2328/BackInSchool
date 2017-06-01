package com.choi.minjoon.backinschool.views.home.sub.first;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.choi.minjoon.backinschool.R;
import com.choi.minjoon.backinschool.common.domain.home.FirstAdapter;
import com.choi.minjoon.backinschool.common.domain.home.HomeData;
import com.choi.minjoon.backinschool.common.domain.home.FirstItem;
import com.choi.minjoon.backinschool.common.receiver.FragmentReceiver;
import com.choi.minjoon.backinschool.core.http.HomeService;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * Created by User on 2017-06-01.
 */

public class FirstFragment extends Fragment {

    FragmentReceiver mCallback;

    HomeService homeService;
    Call<List<FirstItem>> call;
    FirstAdapter firstAdapter;
    RecyclerView.LayoutManager manager;

    @BindView(R.id.recycler_container)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.reloadBtn)
    Button button1;


    String argText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if (b != null) {
            argText = b.getString("text");
        }

        firstAdapter = new FirstAdapter(HomeData.FirstList);
        firstAdapter.setItemClick(new FirstAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                //클릭시 실행될 함수 작성
                if (mCallback != null) {
                    mCallback.receiveMsg(((Integer) position).toString());
                }
            }
        });

        homeService = HomeService.retrofit.create(HomeService.class);
        call = homeService.FirstItems();
        new FirstListRefresh().execute(call);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, v);

        manager = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.scrollToPosition(0);
        recyclerView.setAdapter(firstAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentReceiver) {
            mCallback = (FragmentReceiver) context;
        } else {
            Log.i("MSG", "context is not FirstReceiver");
        }
    }


    @OnClick(R.id.reloadBtn)
    public void firstFragBtnClicked(View view) {

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        homeService = HomeService.retrofit.create(HomeService.class);
        call = homeService.FirstItems();
        new FirstListRefresh().execute(call);

        Snackbar.make(view, "Reloaded", Snackbar.LENGTH_SHORT).show();

    }

    private class FirstListRefresh extends AsyncTask<Call, Void, List<FirstItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<FirstItem> doInBackground(Call... params) {
            try {
                Call<List<FirstItem>> call = params[0];
                HomeData.FirstList = call.execute().body();
                firstAdapter.replace(HomeData.FirstList);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return HomeData.FirstList;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<FirstItem> result) {
            firstAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }
}
