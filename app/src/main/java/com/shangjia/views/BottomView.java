package com.shangjia.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shangjia.AboutUsActivity;
import com.shangjia.CenterActivity;
import com.shangjia.R;
import com.shangjia.ScanInputMoneyActivity;
import com.shangjia.SettingActivity;
import com.shangjia.UpdateActivity;


public class BottomView extends LinearLayout {
    private TextView mScanMenu;
    private TextView mSettingMenu;
    private TextView mUpdatingMenu;
    private TextView mAboutUsMenu;

    public BottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_bottom, this, true);
        mScanMenu = (TextView)findViewById(R.id.id_bottom_menu_scan);
        mSettingMenu = (TextView)findViewById(R.id.id_bottom_menu_setting);
        mUpdatingMenu = (TextView)findViewById(R.id.id_bottom_menu_update);
        mAboutUsMenu = (TextView)findViewById(R.id.id_bottom_menu_aboutus);
        setListener();
    }

    private void setListener(){
        mScanMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScanInputMoneyActivity.class);
                getContext().startActivity(intent);
                cleanCurrentActivity();
            }
        });
        mSettingMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                getContext().startActivity(intent);
                cleanCurrentActivity();
            }
        });
        mUpdatingMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UpdateActivity.class);
                getContext().startActivity(intent);
                cleanCurrentActivity();
            }
        });
        mAboutUsMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AboutUsActivity.class);
                getContext().startActivity(intent);
                cleanCurrentActivity();
            }
        });
    }
    private void cleanCurrentActivity(){
        if(!(((Activity) getContext()) instanceof CenterActivity)){
            ((Activity) getContext()).finish();
        }
    }


}
