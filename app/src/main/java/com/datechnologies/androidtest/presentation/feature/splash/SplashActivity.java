package com.datechnologies.androidtest.presentation.feature.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.databinding.ActivitySplashBinding;
import com.datechnologies.androidtest.presentation.feature.main.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_MILLISECONDS = 1500;

    private static final boolean FORCE_DELAYED_ACTIVITY_LAUNCH = true;

    private Handler handler;

    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);

        super.onCreate(savedInstanceState);
        ActivitySplashBinding mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        if (FORCE_DELAYED_ACTIVITY_LAUNCH){
            runnable = this::launchMainActivity;
            handler = new Handler();
            handler.postDelayed(runnable, SPLASH_TIME_MILLISECONDS);
        } else {
            launchMainActivity();
        }
    }

    private void launchMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && runnable != null){
            handler.removeCallbacks(runnable);
            handler = null;
            runnable = null;
        }
    }
}
