package com.datechnologies.androidtest.presentation.ui_model;


import com.datechnologies.androidtest.domain.pojo.ChatMessage;
import com.datechnologies.androidtest.domain.pojo.LoginMessage;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Converts objects from Domain layer into Presentation layer
 */
public class ModelMapper {

    public static List<ChatUiModel> fromDomain(@NonNull List<ChatMessage> chatMessages){
        List<ChatUiModel> uiModels = new ArrayList<>();
        for (ChatMessage m: chatMessages
             ) {
            ChatUiModel u = new ChatUiModel();
            if (m == null) break;

            u.setUserId(m.getUserId());
            u.setUsername(m.getUsername());
            u.setMessage(m.getMessage());
            u.setAvatarUrl(m.getAvatarUrl());

            uiModels.add(u);
        }
        return uiModels;
    }

    public static LoginUiModel fromDomain(@NonNull LoginMessage loginMessage){

        LoginUiModel u = new LoginUiModel();
        u.setCode(loginMessage.getCode());
        u.setMessage(loginMessage.getMessage());
        return u;
    }
}
