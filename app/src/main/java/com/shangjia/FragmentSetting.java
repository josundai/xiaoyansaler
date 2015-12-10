package com.shangjia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shangjia.views.HeaderView;


public class FragmentSetting extends Fragment {

    TextView mGeneralInfo;
    TextView mChangePwd;
    TextView mShoppingHistory;
    TextView mPointsHistory;
    TextView mIngotsHistory;
    TextView mAppSettings;
    TextView mCheckUpdates;
    TextView mHelp;
    TextView mAboutUs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        HeaderView headerView = (HeaderView) view.findViewById(R.id.header);
        headerView.hideRightImage();
        headerView.setTitle(R.string.settings);
        headerView.setTitleCenter();

        mGeneralInfo = (TextView) view.findViewById(R.id.id_text_general_info);
        mChangePwd = (TextView) view.findViewById(R.id.id_text_change_pwd);
        mShoppingHistory = (TextView) view.findViewById(R.id.id_text_shopping_history);
        mPointsHistory = (TextView) view.findViewById(R.id.id_text_points_exchange_history);
        mIngotsHistory = (TextView) view.findViewById(R.id.id_text_ingots_exchange_history);
        mAppSettings = (TextView) view.findViewById(R.id.id_text_app_settings);
        mCheckUpdates = (TextView) view.findViewById(R.id.id_text_check_updates);
        mHelp = (TextView) view.findViewById(R.id.id_text_help);
        mAboutUs = (TextView) view.findViewById(R.id.id_text_about);

        addListeners();
        return view;
    }

    private void addListeners(){
        mGeneralInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mChangePwd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mShoppingHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mPointsHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mIngotsHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mAppSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mCheckUpdates.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mAboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
