package com.datechnologies.androidtest.presentation.feature.login;

import android.util.Log;

import com.datechnologies.androidtest.data.network.RemoteLoginService;
import com.datechnologies.androidtest.data.repo.LoginRepoImpl;
import com.datechnologies.androidtest.domain.interactor.GetLoginMessage;
import com.datechnologies.androidtest.domain.pojo.LoginMessage;
import com.datechnologies.androidtest.presentation.ui_model.LoginUiModel;
import com.datechnologies.androidtest.presentation.ui_model.ModelMapper;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableSingleObserver;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    private final GetLoginMessage mGetLoginMessage;

    private MutableLiveData<LoginUiModel> mLoginModel;

    public LoginViewModel() {
        mGetLoginMessage = new GetLoginMessage(new LoginRepoImpl(new RemoteLoginService()));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mGetLoginMessage.dispose();
    }

    public void handleLogin(String email, String password){
        Log.d(TAG, "handleLogin()");
        long startTime = System.currentTimeMillis();

        mGetLoginMessage.execute(new DisposableSingleObserver<LoginMessage>() {
            @Override
            public void onSuccess(@NonNull LoginMessage loginMessage) {
                LoginUiModel l = ModelMapper.fromDomain(loginMessage); // Convert into ui model
                l.setValid(true);
                l.setErrorMessage(null);
                long endTime = System.currentTimeMillis(); // Calculate query time length
                l.setRequestCompletionTime(endTime - startTime); // Add totalTime to the ui model

                mLoginModel.postValue(l); // Post ui model
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, e.toString());
                LoginUiModel l = new LoginUiModel(); //Create temp model for error message
                l.setValid(false);
                l.setErrorMessage("Invalid email or password");
                l.setRequestCompletionTime(System.currentTimeMillis() - startTime);
                mLoginModel.postValue(l);
            }

        }, GetLoginMessage.Params.withEmailAndPassword(email, password));
    }

    public MutableLiveData<LoginUiModel> getLoginModel() {
        if (mLoginModel == null){
            mLoginModel = new MutableLiveData<>();
            Log.d(TAG, "initializing mutable live data for the first time");
        }
        return mLoginModel;
    }
}
