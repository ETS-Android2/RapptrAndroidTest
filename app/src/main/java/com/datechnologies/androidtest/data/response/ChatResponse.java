package com.datechnologies.androidtest.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChatResponse {

    @SerializedName("data")
    List<ChatLogMessageModel> chats = new ArrayList<>();

    public List<ChatLogMessageModel> getChats() {
        return chats;
    }

    public void setChats(List<ChatLogMessageModel> chats) {
        this.chats = chats;
    }
}
