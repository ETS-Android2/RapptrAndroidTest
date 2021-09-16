package com.datechnologies.androidtest.data.response;

import com.google.gson.annotations.SerializedName;

/**
 * A data model that represents a chat log message fetched from the D & A Technologies Web Server.
 */

public class ChatLogMessageModel
{
    @SerializedName("user_id")
    public int userId;

    @SerializedName("name")
    public String username;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("message")
    public String message;
}
