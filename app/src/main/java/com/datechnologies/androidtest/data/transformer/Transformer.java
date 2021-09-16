package com.datechnologies.androidtest.data.transformer;

import com.datechnologies.androidtest.data.response.ChatLogMessageModel;
import com.datechnologies.androidtest.data.response.ChatResponse;
import com.datechnologies.androidtest.data.response.LoginResponse;
import com.datechnologies.androidtest.domain.pojo.ChatMessage;
import com.datechnologies.androidtest.domain.pojo.LoginMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to convert Api raw responses into Java objects sent to domain layer
 */
public class Transformer {

    /**
     *
     * @param chatResponse body of the response
     * @return list of POJOs containing chat data that can be transferred through domain layer
     * and subsequently used in the presentation layer
     */
    public static List<ChatMessage> toDomain(ChatResponse chatResponse){
        List<ChatMessage> messageList = new ArrayList<>();
        List<ChatLogMessageModel> modelList = chatResponse.getChats();
        for (ChatLogMessageModel m: modelList
             ) {
            ChatMessage d = new ChatMessage();
            d.setUserId(m.userId);
            d.setUsername(m.username);
            d.setMessage(m.message);
            d.setAvatarUrl(m.avatarUrl);
            messageList.add(d);
        }
        return messageList;
    }

    public static LoginMessage toDomain(LoginResponse loginResponse){
        LoginMessage m = new LoginMessage();
        m.setMessage(loginResponse.message);
        m.setCode(loginResponse.code);
        return m;
    }
}
