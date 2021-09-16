package com.datechnologies.androidtest.data.api;

import com.datechnologies.androidtest.data.response.ChatResponse;

import io.reactivex.Single;
import retrofit2.http.GET;


public interface ChatApi {

    String BASE_URL = "http://dev.rapptrlabs.com/Tests/scripts/";
    @GET("chat_log.php")
    Single<ChatResponse> loadChats();

}
