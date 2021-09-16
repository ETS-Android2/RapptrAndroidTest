package com.datechnologies.androidtest.domain.interactor;

import com.datechnologies.androidtest.domain.pojo.ChatMessage;
import com.datechnologies.androidtest.domain.repository.ChatRepository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Use case for loading chats for use within application
 *
 */
public class GetChats extends BaseUseCase<List<ChatMessage>, Void> {

    private final ChatRepository mChatRepo;

    public GetChats(ChatRepository chatRepo) {
        mChatRepo = chatRepo;
    }

    @Override
    protected Single<List<ChatMessage>> build(Void unused) {
        return mChatRepo.getChatMessages();
    }

    public void execute(DisposableSingleObserver<List<ChatMessage>> observer){
        final Single<List<ChatMessage>> single = this.build(null)
                .subscribeOn(Schedulers.io()) // network calls on io thread
                .observeOn(AndroidSchedulers.mainThread()); // show result on main ui thread
        addDisposable(single.subscribeWith(observer));
    }
}
