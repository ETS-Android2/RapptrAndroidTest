package com.datechnologies.androidtest.presentation.feature.animation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.databinding.ActivityAnimationBinding;
import com.datechnologies.androidtest.presentation.BaseActivity;

import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;

/**
 * Screen that displays the D & A Technologies logo.
 * The icon can be moved around on the screen as well as animated.
 * */

public class AnimationActivity extends BaseActivity {

    private static final String TAG = "AnimationActivity";

    private static final int FADE_DURATION_MILLIS = 1500;

    private static final float FADE_OUT_INTERPOLATOR = 1.2f;

    private static final float FADE_IN_INTERPOLATOR = 1.2f;

    private static final boolean ENABLE_BONUS_BACKGROUND_ANIMATION = true;

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private View mRoot;

    private ImageView mLogo;
    private ActivityAnimationBinding mBinding;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, AnimationActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityAnimationBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mRoot = mBinding.getRoot();

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.animation);

        mLogo = mBinding.logo;

        // Handle drag events via onTouchListener
        mLogo.setOnTouchListener((view, event) -> {
            if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {

                /* Offset x, y values by half of respective width and height measurements of logo
                    in order to recenter while dragging
                 */
                int xOffset = (int) (mLogo.getWidth() / 2); //half of logo width
                int yOffset = (int) (mLogo.getHeight() / 2);//half of logo height

                mLogo.setX(mLogo.getX() + event.getX() - xOffset);
                mLogo.setY(mLogo.getY() + event.getY() - yOffset);
            }
            return true;
        });

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator(FADE_OUT_INTERPOLATOR));
        fadeOut.setDuration(FADE_DURATION_MILLIS);

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator(FADE_IN_INTERPOLATOR));
        fadeIn.setDuration(FADE_DURATION_MILLIS);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLogo.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Initialize the background animations
                if (ENABLE_BONUS_BACKGROUND_ANIMATION){
                    bonusAnimatedBackground();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mBinding.buttonFade.setOnClickListener(view -> mLogo.startAnimation(fadeOut));

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // TODO: Add a ripple effect when the buttons are clicked

        // TODO: When the fade button is clicked, you must animate the D & A Technologies logo.
        // TODO: It should fade from 100% alpha to 0% alpha, and then from 0% alpha to 100% alpha

        // TODO: The user should be able to touch and drag the D & A Technologies logo around the screen.

        // TODO: Add a bonus to make yourself stick out. Music, color, fireworks, explosions!!!

    }

    /**
     * Will animate the background after logo has faded in
     */
    private void bonusAnimatedBackground(){
        mRoot.setBackground(ContextCompat.getDrawable(AnimationActivity.this, R.drawable.background_animated));
        AnimationDrawable drawable = (AnimationDrawable) mRoot.getBackground();
        drawable.setEnterFadeDuration(1500);
        drawable.setExitFadeDuration(1500);
        drawable.start();
    }
}
