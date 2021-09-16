package com.datechnologies.androidtest.data.api;

import com.datechnologies.androidtest.data.response.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    String BASE_URL = "http://dev.rapptrlabs.com/Tests/scripts/";

    @FormUrlEncoded
    @POST("login.php")
    Single<LoginResponse> login(@Field("email") String email, @Field("password") String password);
}
