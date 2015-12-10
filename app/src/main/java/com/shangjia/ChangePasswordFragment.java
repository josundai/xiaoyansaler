package com.shangjia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shangjia.service.TemporyLoginService;

/**
 * A login screen that offers login via email/password.
 */
public class ChangePasswordFragment extends Fragment {

    TextView oldPassword ;
    TextView newPassword;
    TextView newPasswordAgain;
    Button mSubmitChange;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_changepassword, container, false);

        oldPassword = (TextView) view.findViewById(R.id.id_old_password);
        newPassword = (TextView) view.findViewById(R.id.id_new_password);
        newPasswordAgain = (TextView) view.findViewById(R.id.id_new_password_again);
        mSubmitChange = (Button)view.findViewById(R.id.submit_changepassword_button);

        mSubmitChange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                attemptChangePassword();
            }
        });
        return view;
    }

    private void attemptChangePassword() {

        // Reset errors.
        oldPassword.setError(null);
        newPassword.setError(null);
        newPasswordAgain.setError(null);

        // Store values at the time of the login attempt.
        String oldP = oldPassword.getText().toString();
        String newP = newPassword.getText().toString();
        String doubleNewP = newPasswordAgain.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(oldP)) {
            //TODO: verify old passWord.
            oldPassword.setError(getString(R.string.error_invalid_password));
            focusView = oldPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(newP)) {
            //TODO: valid the new passWord format.
            newPassword.setError(getString(R.string.error_null_new_password));
            focusView = newPassword;
            cancel = true;
        }else{
            if (TextUtils.isEmpty(doubleNewP)) {
                //TODO: verify old passWord.
                newPasswordAgain.setError(getString(R.string.error_null_new_password));
                focusView = newPasswordAgain;
                cancel = true;
            }else{
                if( !newP.equals(doubleNewP)){
                    newPasswordAgain.setError(getString(R.string.error_invalid_double_password));
                    focusView = newPasswordAgain;
                    cancel = true;
                }
            }
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            //TODO: change the real password;
            TemporyLoginService.changePassword();
            AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordFragment.this.getActivity());
            builder.setTitle("修改密码成功!");
            builder.setMessage("成功修改密码!");
            builder.setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            oldPassword.clearComposingText();
                            newPassword.clearComposingText();
                            newPasswordAgain.clearComposingText();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.show();

        }
    }



}

