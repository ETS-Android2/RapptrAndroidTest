package com.datechnologies.androidtest.data.repo;

import com.datechnologies.androidtest.data.network.RemoteLoginService;
import com.datechnologies.androidtest.data.transformer.Transformer;
import com.datechnologies.androidtest.domain.pojo.LoginMessage;
import com.datechnologies.androidtest.domain.repository.LoginRepository;

import io.reactivex.Single;

public class LoginRepoImpl implements LoginRepository {

    private final RemoteLoginService mRemoteLoginService;

    public LoginRepoImpl(RemoteLoginService service) {
        mRemoteLoginService = service;
    }


    /** Method for obtaining login message and code when an email and password are providing
     *
     * By delegating implementation to a separate service class, future improvements or alternate
     * implementations might be added such as a local data store, storage in shared prefs etc.
     *
     * @param email email as a String to be sent as part of form
     * @param password password as a String to be sent as part of form
     * @return RxJava Single to be consumed in the presentation layer after passing through the domain
     */
    @Override
    public Single<LoginMessage> login(String email, String password) {
        return mRemoteLoginService.login(email, password).map(Transformer::toDomain);
    }
}
