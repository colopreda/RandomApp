package com.apredazzi.randomapplication.detail;

import android.support.annotation.NonNull;
import com.apredazzi.randomapplication.BasePresenter;
import com.apredazzi.randomapplication.pojo.Users;

public class DetailPresenter extends BasePresenter implements DetailContract.Presenter {

    private DetailContract.View mView;

    public DetailPresenter(@NonNull DetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loadData(final Users user) {
        mView.loadImage(user.getPicture().getLarge());
        mView.loadPersonalData(user.getName().getFirst() + " " + user.getName().getLast(),
                user.getLogin().getUsername(), user.getEmail());
    }
}
