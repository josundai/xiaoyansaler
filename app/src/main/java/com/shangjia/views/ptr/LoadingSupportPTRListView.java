package com.shangjia.views.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.shangjia.R;

public class LoadingSupportPTRListView extends LinearLayout {
	private final static String tag = "TT-LoadingSupportPTR";
	private CustomizedPTRListView mListView;
	private View mLoadingView;
	private ListAdapter mListAdapter;
    private Mode mRefreshMode = Mode.BOTH;

	public LoadingSupportPTRListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.view_ptr_list_layout, this, true);
		mListView = (CustomizedPTRListView)findViewById(R.id.id_content);
        mListView.setVisibility(View.GONE);
        mListView.setMode(mRefreshMode);

		mLoadingView = findViewById(R.id.id_loading);
		mLoadingView.setVisibility(View.GONE);
	}

	/**
	 * set the refresh mode: START or END
	 * @param mode
	 */
	public void setMode(Mode mode){
        if(mode != null)
            mListView.setMode(mode);
	}

	public void addViewToListHeader(View view, int height){
		mListView.addViewToListHeader(view, height);
	}

    public final void showLoadingView() {
		mLoadingView.setVisibility(View.VISIBLE);
		mListView.setVisibility(View.GONE);
	}

    public final void showListView() {
		mLoadingView.setVisibility(View.GONE);
		mListView.setVisibility(View.VISIBLE);
	}

    public final void refreshComplete(){
		mListView.onRefreshComplete();
	}

    public void setOnRefreshListener(PullToRefreshBase.OnRefreshListener2 listener){
        mListView.setOnRefreshListener(listener);
    }
}