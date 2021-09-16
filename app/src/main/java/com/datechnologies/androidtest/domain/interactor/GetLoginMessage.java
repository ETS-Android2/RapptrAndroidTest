package com.datechnologies.androidtest.domain.interactor;

import com.datechnologies.androidtest.domain.pojo.LoginMessage;
import com.datechnologies.androidtest.domain.repository.LoginRepository;

import io.reactivex.Single;


/**
 *  Use Case for getting login response when email, password provided
 */
public class GetLoginMessage extends BaseUseCase<LoginMessage, GetLoginMessage.Params>  {

    private final LoginRepository mLoginRepo;

    public GetLoginMessage(LoginRepository loginRepo) {
        mLoginRepo = loginRepo;
    }

    @Override
    protected Single<LoginMessage> build(Params params) {
        return mLoginRepo.login(params.email, params.password);
    }

    public static final class Params{

        private final String email;
        private final String password;

         private Params(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public static Params withEmailAndPassword(String email, String password){
            return new Params(email, password);
        }
    }
}
