package com.apredazzi.randomapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.apredazzi.randomapplication.BaseActivity;
import com.apredazzi.randomapplication.R;
import com.apredazzi.randomapplication.detail.DetailActivity;
import com.apredazzi.randomapplication.pojo.Users;

public class HomeActivity extends BaseActivity implements HomeFragment.OnHomeFragmentInteractionListener {

    private static final String PERSON_DETAILS = "PERSON_DETAILS";
    HomeContract.Presenter mPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_layout);

        HomeFragment HomeFragment = (HomeFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frame_layout_content);
        if (HomeFragment == null) {
            HomeFragment = HomeFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_layout_content, HomeFragment);
            transaction.commit();
        }
        mPresenter = new HomePresenter(HomeFragment);
        mPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.stop();
    }

    @Override
    public void onUserDetailsReceived(final Users user) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(PERSON_DETAILS, user);
        startActivity(intent);
    }
}
