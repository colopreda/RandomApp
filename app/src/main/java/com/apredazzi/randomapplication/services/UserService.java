package com.apredazzi.randomapplication.services;

import com.apredazzi.randomapplication.pojo.Results;
import com.apredazzi.randomapplication.pojo.Users;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {
    @GET("api/")
    Call<Results> listUsers(@Query("results") int numberOfUsers);
}
