package com.datechnologies.androidtest.data.network;

import com.datechnologies.androidtest.data.api.LoginApi;
import com.datechnologies.androidtest.data.response.LoginResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteLoginService {

    private final LoginApi mLoginApi;

    public RemoteLoginService() {
        // Create the retrofit client
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Add gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Support RxJava
                .build();

        // Create login api
        mLoginApi = retrofit.create(LoginApi.class);
    }

    public Single<LoginResponse> login(String email, String password){
        return mLoginApi.login(email, password);
    }


}
