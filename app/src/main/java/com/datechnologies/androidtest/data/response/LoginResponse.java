package com.datechnologies.androidtest.data.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;
}
