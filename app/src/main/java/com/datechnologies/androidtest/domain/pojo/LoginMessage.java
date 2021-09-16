package com.datechnologies.androidtest.domain.pojo;

/**
 * POJO used for transfer Login details through domain layer
 */
public class LoginMessage {

    private String message;

    private String code;

    public LoginMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
