package com.datechnologies.androidtest.domain.repository;

import com.datechnologies.androidtest.domain.pojo.ChatMessage;

import java.util.List;

import io.reactivex.Single;

public interface ChatRepository {

    Single<List<ChatMessage>> getChatMessages();
}
