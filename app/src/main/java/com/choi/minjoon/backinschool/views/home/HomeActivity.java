package com.choi.minjoon.backinschool.views.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.choi.minjoon.backinschool.common.receiver.FragmentReceiver;
import com.choi.minjoon.backinschool.views.base.BaseActivity;
import com.choi.minjoon.backinschool.R;
import com.choi.minjoon.backinschool.views.detail.DetailActivity;
import com.choi.minjoon.backinschool.views.home.sub.first.FirstFragment;
import com.choi.minjoon.backinschool.views.home.sub.second.SecondFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements FragmentReceiver {

    private static final String TAG_F1 = "FIRST";
    private static final String TAG_F2 = "SECOND";


    FrameLayout frameLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;

    LayoutInflater layoutInflater;
    View activityView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        activityView = layoutInflater.inflate(R.layout.home_activity, null, false);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.activity_frame);
        frameLayout.addView(activityView);

        ButterKnife.bind(this, activityView);

        actionBar = HomeActivity.this.getSupportActionBar();
        View viewToolbar = HomeActivity.this.getLayoutInflater().inflate(R.layout.custom_toolbar, null);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        firstFragement(activityView);

    }

    @OnClick(R.id.button1)
    public void firstFragement(View view) {
        Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_F1);
        if (f == null) {
            Bundle b = new Bundle();
            b.putString("text", "first");

            FirstFragment firstFragment = new FirstFragment();
            firstFragment.setArguments(b);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.HomeFragContainer, firstFragment, TAG_F1);
            ft.commit();

            Snackbar.make(view, "First", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    @OnClick(R.id.button1)
    public void secondFragment(View view) {
        Fragment f = getSupportFragmentManager().findFragmentByTag(TAG_F2);
        if (f == null) {
            Bundle b = new Bundle();
            b.putString("text", "second");

            SecondFragment secondFragment = new SecondFragment();
            secondFragment.setArguments(b);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.HomeFragContainer, secondFragment, TAG_F2);
            ft.commit();

            Snackbar.make(view, "Second", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    @OnClick(R.id.fab)
    public void FabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }

    @Override
    public void receiveMsg(String msg) {
        callMainMethod(msg);
    }

    public void callMainMethod(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("position", msg);
        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_out);
    }


}
