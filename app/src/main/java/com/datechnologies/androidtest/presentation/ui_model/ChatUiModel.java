package com.datechnologies.androidtest.presentation.ui_model;

/**
 * Model class representing chat message data, for use only within the UI layer
 *
 * For simple cases, the two objects may be the same
 */
public class ChatUiModel {

    private int userId;

    private String username;

    private String avatarUrl;

    private String message;

    public ChatUiModel() {
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
