package com.apredazzi.randomapplication.detail;

import com.apredazzi.randomapplication.BaseContract;
import com.apredazzi.randomapplication.pojo.Users;

public interface DetailContract extends BaseContract {

    interface View extends BaseContract.View<Presenter> {

        void loadImage(String imageUrl);

        void loadPersonalData(String name, String username, String email);
    }

    interface Presenter extends BaseContract.Presenter {

        void loadData(Users user);
    }
}
