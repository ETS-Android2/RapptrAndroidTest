package com.datechnologies.androidtest.presentation.feature.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.presentation.ui_model.LoginUiModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class LoginDialogFrag extends DialogFragment {

    public static final String FRAG_TAG = "LoginDialogFrag";

    private static final String ARG_PREFIX = "LoginDialogFrag_arg_";

    private static final String ARG_CODE = ARG_PREFIX + "code";

    private static final String ARG_MESSAGE = ARG_PREFIX + "message";

    private static final String ARG_MILLIS = ARG_PREFIX + "millis";

    private static final String TAG = "LoginDialogFrag";

    public static LoginDialogFrag newInstance(LoginUiModel loginUiModel) {

        Bundle args = new Bundle();
        args.putString(ARG_CODE, loginUiModel.getCode());
        args.putString(ARG_MESSAGE, loginUiModel.getMessage());
        args.putLong(ARG_MILLIS, loginUiModel.getRequestCompletionTime());

        LoginDialogFrag fragment = new LoginDialogFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog)
    {
        super.onDismiss(dialog);
        if (getActivity() instanceof DialogInterface.OnDismissListener){
            Log.d(TAG, "onDismiss()");
            ((DialogInterface.OnDismissListener) getActivity()).onDismiss(dialog);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.frag_login_dialog, null);

        String code = getArguments().getString(ARG_CODE);
        String message = getArguments().getString(ARG_MESSAGE);
        long millis = getArguments().getLong(ARG_MILLIS);


        TextView tv_Title = view.findViewById(R.id.title);
        tv_Title.setText(code);
        TextView tv_Message = view.findViewById(R.id.message);
        tv_Message.setText(message);
        TextView tv_SubMessage = view.findViewById(R.id.sub_message);
        String m = String.format("API query took %s milliseconds", millis);
        tv_SubMessage.setText(m);


        builder.setView(view)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    if (getActivity() instanceof DialogPressListener){
                        ((DialogPressListener)getActivity()).onClickOk();
                    }
                });
        return builder.create();
    }



    public interface DialogPressListener{
        void onClickOk();
    }
}
