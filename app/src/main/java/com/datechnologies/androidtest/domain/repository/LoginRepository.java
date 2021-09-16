package com.datechnologies.androidtest.domain.repository;

import com.datechnologies.androidtest.domain.pojo.LoginMessage;

import io.reactivex.Single;

public interface LoginRepository {

    Single<LoginMessage> login(String email, String password);
}
