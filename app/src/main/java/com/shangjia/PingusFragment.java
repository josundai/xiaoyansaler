package com.shangjia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class PingusFragment extends Fragment {


    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private EditText mAccountView;
    private EditText mPasswordView;

    private View mProgressView;
    private View mLoginFormView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pingus);
        View view = inflater.inflate(R.layout.fragment_pingus, container, false);

//        HeaderView headerView = (HeaderView) view.findViewById(R.id.header);
//        headerView.hideRightImage();
//        headerView.setTitle(R.string.settings);
//        headerView.setTitleCenter();

        return view;
    }



}

