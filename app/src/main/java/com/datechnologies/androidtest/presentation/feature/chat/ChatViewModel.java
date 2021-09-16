package com.datechnologies.androidtest.presentation.feature.chat;

import android.util.Log;

import com.datechnologies.androidtest.data.network.RemoteChatService;
import com.datechnologies.androidtest.data.repo.ChatRepoImpl;
import com.datechnologies.androidtest.domain.interactor.GetChats;
import com.datechnologies.androidtest.domain.pojo.ChatMessage;
import com.datechnologies.androidtest.presentation.ui_model.ChatUiModel;
import com.datechnologies.androidtest.presentation.ui_model.ModelMapper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.observers.DisposableSingleObserver;

public class ChatViewModel extends ViewModel {

    private static final String TAG = "ChatViewModel";

    static final int STATUS_LOADING = 0;

    static final int STATUS_SUCCESS = 1;

    static final int STATUS_ERROR = 2;

    private MutableLiveData<List<ChatUiModel>> mChats = new MutableLiveData<>();

    private MutableLiveData<Integer> mStatus = new MutableLiveData<>(STATUS_LOADING);


    private final GetChats mGetChats;


    public ChatViewModel() {
        mGetChats = new GetChats(new ChatRepoImpl(new RemoteChatService()));
        start();
    }

    public void start(){

        mStatus.postValue(STATUS_LOADING);

        mGetChats.execute(new DisposableSingleObserver<List<ChatMessage>>() {
            @Override
            public void onSuccess(@NonNull List<ChatMessage> chatMessages) {
                mChats.postValue(ModelMapper.fromDomain(chatMessages));
                mStatus.postValue(STATUS_SUCCESS);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, e.toString());
                mStatus.postValue(STATUS_ERROR);
            }
        });


    }

    public LiveData<List<ChatUiModel>> getChats() {
        if (mChats == null){
            mChats = new MutableLiveData<>();
        }
        return mChats;
    }

    public LiveData<Integer> getStatus() {
        if (mStatus == null){
            mStatus = new MutableLiveData<>(STATUS_LOADING);
        }
        return mStatus;
    }
}
