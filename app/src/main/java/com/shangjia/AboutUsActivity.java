package com.shangjia;

import android.app.Activity;
import android.os.Bundle;

import com.shangjia.views.HeaderView;

/**
 * Created by dai on 2015/12/16.
 */
public class AboutUsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aboutus);

        HeaderView headerView = (HeaderView) findViewById(R.id.aboutus_header);
//        headerView.hideRightImage();
        headerView.setTitle(R.string.center_about_us);
        headerView.setTitleCenter();

    }
}
