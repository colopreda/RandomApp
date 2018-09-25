package com.apredazzi.randomapplication.home;

import android.support.annotation.NonNull;
import android.util.Log;
import com.apredazzi.randomapplication.BasePresenter;
import com.apredazzi.randomapplication.pojo.Results;
import com.apredazzi.randomapplication.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePresenter extends BasePresenter implements HomeContract.Presenter {

    private static final String BASE_URL = "https://randomuser.me/";
    private HomeContract.View mView;

    public HomePresenter(@NonNull HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        getUsers(50);
    }

    public void getUsers(final int numberOfUsers) {

        UserService service = getUserService();
        Call<Results> users = service.listUsers(numberOfUsers);
        users.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull final Call<Results> call, @NonNull final Response<Results> response) {
                mView.loadUsers(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull final Call<Results> call, @NonNull final Throwable t) {
                Log.d("FAIL", "Fallé al obtener los usuarios");
            }
        });
    }

    private UserService getUserService() {
        Gson gson = new GsonBuilder()
            .setLenient()
            .create();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        return retrofit.create(UserService.class);
    }

    @Override
    public void getMoreUsers(final int numberOfUsers) {
        UserService service = getUserService();
        Call<Results> users = service.listUsers(numberOfUsers);
        users.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(@NonNull final Call<Results> call, @NonNull final Response<Results> response) {
                mView.loadMoreUsers(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull final Call<Results> call, @NonNull final Throwable t) {
                Log.d("FAIL", "Fallé al obtener más usuarios");
            }
        });
    }

    @Override
    public void stop() {
    }
}
