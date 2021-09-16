package com.datechnologies.androidtest.presentation;

import android.text.TextUtils;
import android.util.Patterns;

public class PresentationUtils {

    public static boolean isValidEmail(String input){
        return (!TextUtils.isEmpty(input) && Patterns.EMAIL_ADDRESS.matcher(input).matches());
    }
}
