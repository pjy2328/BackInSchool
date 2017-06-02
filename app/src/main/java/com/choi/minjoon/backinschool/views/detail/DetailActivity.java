package com.choi.minjoon.backinschool.views.detail;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.choi.minjoon.backinschool.R;
import com.choi.minjoon.backinschool.common.domain.detail.DetailItem;
import com.choi.minjoon.backinschool.core.http.DetailService;
import com.choi.minjoon.backinschool.views.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.detail_text)
    TextView detailText;

    DetailService detailService;
    Call<List<DetailItem>> call;

    LayoutInflater layoutInflater;
    View activityView;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        activityView = layoutInflater.inflate(R.layout.detail_activity, null, false);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_frame);
        frameLayout.addView(activityView);

        ButterKnife.bind(this,activityView);

        actionBar = DetailActivity.this.getSupportActionBar();
        View viewToolbar = DetailActivity.this.getLayoutInflater().inflate(R.layout.custom_toolbar, null);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        detailText = (TextView) activityView.findViewById(R.id.detail_text);

    }
    @OnClick(R.id.fab)
    public void FabClick(View view){
        detailService = DetailService.retrofit.create(DetailService.class);
        call = detailService.getItems();
        new DetailDataLoad().execute(call);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
    }
    private class DetailDataLoad extends AsyncTask<Call, Void, List<DetailItem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<DetailItem> doInBackground(Call... params) {
            try {
                Call<List<DetailItem>> call = params[0];
                return call.execute().body();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<DetailItem> result) {
            detailText.setText(result.get(0).getUserId());
        }

    }
}
