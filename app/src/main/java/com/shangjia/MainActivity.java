package com.shangjia;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;




public class MainActivity extends FragmentActivity {

    private static final String TAG = "XYK-MAIN";
    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    // 定义一个布局
    private LayoutInflater layoutInflater;
    private View mCustomView;
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = {FragmentHome.class, QueryDealListFragment.class, ChangePasswordFragment.class, PingusFragment.class};
    // 定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.receivemoney, R.drawable.querydeals, R.drawable.changepassword, R.drawable.pingus};
    // Tab选项卡的文字
    private int mTextviewArray[] = {R.string.receivemoney, R.string.querydeals, R.string.changepassword, R.string.pingus};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void showPopup(View v) {
    }

    /**
     * 初始化组件
     */
    private void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realTabContent);
        // 得到fragment的个数
        for (int i = 0; i < fragmentArray.length; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i])).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.main_tab_item_bg);
        }
        mTabHost.setCurrentTab(3);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.main_tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);
        return view;
    }
}
