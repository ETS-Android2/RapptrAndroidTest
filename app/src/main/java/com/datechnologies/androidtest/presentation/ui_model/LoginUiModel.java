package com.datechnologies.androidtest.presentation.ui_model;


public class LoginUiModel {

    public String code;

    public String message;

    public long requestCompletionTime;

    public boolean isValid;

    private String errorMessage;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getRequestCompletionTime() {
        return requestCompletionTime;
    }

    public void setRequestCompletionTime(long requestCompletionTime) {
        this.requestCompletionTime = requestCompletionTime;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        this.isValid = valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
