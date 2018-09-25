package com.apredazzi.randomapplication.home;

import com.apredazzi.randomapplication.BaseContract;
import com.apredazzi.randomapplication.pojo.Users;
import java.util.List;
import retrofit2.Response;

public interface HomeContract extends BaseContract {

    interface View extends BaseContract.View<Presenter> {
        void loadMoreUsers(List<Users> users);
        void loadUsers(List<Users> users);
    }

    interface Presenter extends BaseContract.Presenter {
        void getUsers(final int numberOfUsers);
        void getMoreUsers(final int numberOfUsers);
    }
}
