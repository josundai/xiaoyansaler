package com.shangjia;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shangjia.service.TemporyLoginService;
import com.shangjia.views.HeaderView;

/**
 * A login screen that offers login via email/password.
 */
public class ChangePasswordActivity extends Activity {

    TextView oldPassword ;
    TextView newPassword;
    TextView newPasswordAgain;
    Button mSubmitChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_changepassword);

        HeaderView headerView = (HeaderView) findViewById(R.id.header);
//        headerView.hideRightImage();
        headerView.setTitle(R.string.center_change_password);
//        headerView.setTitleCenter();

        oldPassword = (TextView) findViewById(R.id.id_old_password);
        newPassword = (TextView) findViewById(R.id.id_new_password);
        newPasswordAgain = (TextView) findViewById(R.id.id_new_password_again);
        mSubmitChange = (Button) findViewById(R.id.submit_changepassword_button);

        mSubmitChange.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                attemptChangePassword();
            }
        });

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


        if (TextUtils.isEmpty(newP)) {
            //TODO: valid the new passWord format.
            newPassword.setError(getString(R.string.error_null_new_password));
            focusView = newPassword;
            cancel = true;
            if (TextUtils.isEmpty(doubleNewP)) {
                //TODO: verify old passWord.
                newPasswordAgain.setError(getString(R.string.error_null_new_password));
                cancel = true;
            }
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
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(oldP)) {
            //TODO: verify old passWord.
            oldPassword.setError(getString(R.string.error_null_old_password));
            focusView = oldPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //TODO: change the real password;
            TemporyLoginService.changePassword();
            AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
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

