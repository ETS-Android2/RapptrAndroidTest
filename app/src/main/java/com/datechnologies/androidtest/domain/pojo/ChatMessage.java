package com.datechnologies.androidtest.domain.pojo;


/**
 * POJO used for transfer Chat through domain layer
 */
public class ChatMessage {

    private int userId;

    private String username;

    private String avatarUrl;

    private String message;

    public ChatMessage() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
