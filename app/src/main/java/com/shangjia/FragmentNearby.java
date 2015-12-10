package com.shangjia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentNearby extends Fragment {
    private static final String TAG = "XYK-FragmentNearby";
    private HorizontalScrollView mTabScrollView;
    private List<LinearLayout> mTabs;
    private View mLoadingView;
    private TabHost mTabHost;
    private ViewPager mViewPager;

    private String[] shopCategories = {"餐饮类","医疗类","娱乐类","百货类", "出租类", "旅游类", "文化类", "AA类"};
    private TabHost.TabContentFactory mEmptyTabContentFactory = new TabHost.TabContentFactory(){
        public View createTabContent(String tag) {
            return new TextView(getActivity());
        }
    };

    public FragmentNearby(){
        Log.d(TAG, "FragmentNearby()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }


}
