package com.datechnologies.androidtest.data.repo;

import com.datechnologies.androidtest.data.network.RemoteChatService;
import com.datechnologies.androidtest.data.transformer.Transformer;
import com.datechnologies.androidtest.domain.pojo.ChatMessage;
import com.datechnologies.androidtest.domain.repository.ChatRepository;

import java.util.List;

import io.reactivex.Single;

public class ChatRepoImpl implements ChatRepository {

    private final RemoteChatService mRemoteChatService;

    public ChatRepoImpl(RemoteChatService chatService) {
        mRemoteChatService = chatService;
    }

    @Override
    public Single<List<ChatMessage>> getChatMessages() {

        //Return the result and map the response into the domain object
        return mRemoteChatService.getChatMessages().map(Transformer::toDomain);
    }
}
