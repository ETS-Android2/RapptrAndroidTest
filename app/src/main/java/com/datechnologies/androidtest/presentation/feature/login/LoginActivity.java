package com.datechnologies.androidtest.presentation.feature.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.databinding.ActivityLoginBinding;
import com.datechnologies.androidtest.presentation.BaseActivity;
import com.datechnologies.androidtest.presentation.PresentationUtils;
import com.datechnologies.androidtest.presentation.ui_model.LoginUiModel;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * A screen that displays a login prompt, allowing the user to login to the D & A Technologies Web Server.
 *
 */
public class LoginActivity extends BaseActivity implements LoginDialogFrag.DialogPressListener {

    private static final String TAG = "LoginActivity";

    private static final int MIN_PASSWORD_LENGTH = 6; // constant to ensure valid passwords are sent

    private LoginViewModel mViewModel;

    private ActivityLoginBinding mBinding;

    //==============================================================================================
    // Static Class Methods


    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setTitle(R.string.login);

//        addCorrectEmailPassword(); for testing purposes only

        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        mViewModel.getLoginModel().observe(this, u -> {
            if (u.isValid){
                showAlertDialog(u);
            } else{
                long requestTime = u.getRequestCompletionTime();
                String e = u.getErrorMessage() + String.format("\nRequest Time: %s ms",requestTime);
                mBinding.password.setError(e);
            }
        });

        mBinding.buttonLogin.setOnClickListener(view -> {

            String emailStr = mBinding.email.getEditableText().toString();
            String passwordStr = mBinding.password.getEditableText().toString();

            if (formHasValidInputs(emailStr, passwordStr)){
                mViewModel.handleLogin(emailStr, passwordStr);
            }
        });

        // TODO: Make the UI look like it does in the mock-up. Allow for horizontal screen rotation.
        // TODO: Add a ripple effect when the buttons are clicked
        // TODO: Save screen state on screen rotation, inputted username and password should not disappear on screen rotation

        // TODO: Send 'email' and 'password' to http://dev.rapptrlabs.com/Tests/scripts/login.php
        // TODO: as FormUrlEncoded parameters.

        // TODO: When you receive a response from the login endpoint, display an AlertDialog.
        // TODO: The AlertDialog should display the 'code' and 'message' that was returned by the endpoint.
        // TODO: The AlertDialog should also display how long the API call took in milliseconds.
        // TODO: When a login is successful, tapping 'OK' on the AlertDialog should bring us back to the MainActivity

        // TODO: The only valid login credentials are:
        // TODO: email: info@rapptrlabs.com
        // TODO: password: Test123
        // TODO: so please use those to test the login.
    }

    /***
     * Checks EditText input values to ensure they are not blank or formatted incorrectly
     * Will also format ui to show error messages as necessary
     *
     * @return if both email and password inputs are formatted correctly to avoid sending bad requests
     */

    private boolean formHasValidInputs(String emailInput, String passwordInput){

        int numErrors = 0;

        String emailErrorMessage = null;
        String passwordErrorMessage = null;

        if (!PresentationUtils.isValidEmail(emailInput)){
            emailErrorMessage = "Please enter a valid email address";
            numErrors++;
        }
        if (emailInput.isEmpty()){
            emailErrorMessage = "Email address cannot be blank";
            numErrors++;
        }
        if (passwordInput.length() < MIN_PASSWORD_LENGTH){
            passwordErrorMessage = String.format("Passwords must be %s characters long", MIN_PASSWORD_LENGTH);
            numErrors++;
        }
        if (passwordInput.isEmpty()){
            passwordErrorMessage = "Password cannot be blank";
            numErrors++;
        }

        mBinding.email.setError(emailErrorMessage);
        mBinding.password.setError(passwordErrorMessage);

        return (numErrors == 0);

    }

    /**
     * Testing method for adding the correct email/password combo
     */
    private void addCorrectEmailPassword(){
        String username = "info@rapptrlabs.com";
        String password = "Test123";
        mBinding.email.setText(username);
        mBinding.password.setText(password);
    }

    private void showAlertDialog(LoginUiModel model){
        if (getSupportFragmentManager().findFragmentByTag(LoginDialogFrag.FRAG_TAG) == null){
            DialogFragment frag = LoginDialogFrag.newInstance(model);
            frag.show(getSupportFragmentManager(), LoginDialogFrag.FRAG_TAG);
        }
    }

    @Override
    public void onClickOk() {
        finish(); //go back to main activity
    }
}
