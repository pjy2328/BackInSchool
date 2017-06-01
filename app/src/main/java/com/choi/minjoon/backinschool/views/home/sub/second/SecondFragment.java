package com.choi.minjoon.backinschool.views.home.sub.second;

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
import com.choi.minjoon.backinschool.common.domain.home.SecondAdapter;
import com.choi.minjoon.backinschool.common.domain.home.SecondItem;
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

public class SecondFragment extends Fragment {

    FragmentReceiver mCallback;

    HomeService homeService;
    Call<List<SecondItem>> call;
    SecondAdapter secondAdapter;
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

        secondAdapter = new SecondAdapter(HomeData.SecondList);
        secondAdapter.setItemClick(new SecondAdapter.ItemClick() {
            @Override
            public void onClick(View view, int position) {
                //클릭시 실행될 함수 작성
                if (mCallback != null) {
                    mCallback.receiveMsg(((Integer) position).toString());
                }
            }
        });

        homeService = HomeService.retrofit.create(HomeService.class);
        call = homeService.SecondItems();
        new SecondListRefresh().execute(call);

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
        recyclerView.setAdapter(secondAdapter);
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
        call = homeService.SecondItems();
        new SecondListRefresh().execute(call);

        Snackbar.make(view, "Reloaded", Snackbar.LENGTH_SHORT).show();

    }

    private class SecondListRefresh extends AsyncTask<Call, Void, List<SecondItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<SecondItem> doInBackground(Call... params) {
            try {
                Call<List<SecondItem>> call = params[0];
                HomeData.SecondList = call.execute().body();
                secondAdapter.replace(HomeData.SecondList);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return HomeData.SecondList;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<SecondItem> result) {
            secondAdapter.notifyDataSetChanged();
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }
}
