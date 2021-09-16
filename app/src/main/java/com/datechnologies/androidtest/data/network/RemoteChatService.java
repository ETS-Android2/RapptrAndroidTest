package com.datechnologies.androidtest.data.network;

import com.datechnologies.androidtest.data.api.ChatApi;
import com.datechnologies.androidtest.data.response.ChatResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteChatService {

    private final ChatApi mChatApi;

    public RemoteChatService() {
        // Create the retrofit client
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ChatApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Add gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Support RxJava
                .build();

        // Create the chat api
        mChatApi = retrofit.create(ChatApi.class);
    }

   public Single<ChatResponse> getChatMessages(){
        return mChatApi.loadChats();
   }
}
