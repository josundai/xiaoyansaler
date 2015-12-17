package com.shangjia;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.shangjia.actions.AbstractAction;
import com.shangjia.actions.GetOrderHistoryAction;
import com.shangjia.actions.ParallelTask;
import com.shangjia.model.OrderRecord;
import com.shangjia.model.Pagination;
import com.shangjia.views.ptr.CustomizedPTRListView;
import com.shangjia.views.ptr.PTRListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryDealListFragment extends Fragment  implements PullToRefreshBase.OnRefreshListener2<ListView> {
	private final static String tag = "TT-FragNearbyShopList";

	private PTRListAdapter<OrderRecord> dealsListAdaptor;
	private List<OrderRecord> mShopList;
	private boolean mShopLoadedFromServer = false;
	private GetOrderHistoryAction mGetShopAction;

	private View mLayout;
	private CustomizedPTRListView mListView;
	private View mLoadingView;
	private ListAdapter mListAdapter;
	private PullToRefreshBase.Mode mRefreshMode = PullToRefreshBase.Mode.BOTH;

	private int mAsyncTaskCount = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		try {
			mLayout = inflater.inflate(R.layout.view_ptr_list_layout, container, false);
			mListView = (CustomizedPTRListView) mLayout.findViewById(R.id.id_content);

			mListView.setOnRefreshListener(this);
			mLoadingView = mLayout.findViewById(R.id.id_loading);
			//hide both before any data is available
			mListView.setVisibility(View.GONE);
			mListView.setMode(mRefreshMode);
			mLoadingView.setVisibility(View.GONE);
			return mLayout;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Log.d(tag, " onActivityCreated()");
		mAsyncTaskCount = 0;
//		showLoadingView();
        setMode(PullToRefreshBase.Mode.PULL_FROM_END);
		if( !mShopLoadedFromServer )
			loadShopFromServer();
		else
			loadShopFromDB();
	}
	
	private void loadShopFromServer(){
		mAsyncTaskCount ++;
		Log.d(tag,  " loadShopFromServer(): tasks: " + mAsyncTaskCount);
		mGetShopAction = new GetOrderHistoryAction(getActivity(),  1, Constants.PAGE_SIZE);
		mGetShopAction.execute(
                new AbstractAction.BackgroundCallBack<Pagination<OrderRecord>>() {
                    public void onSuccess(Pagination<OrderRecord> newsPage) {
                    }

                    public void onFailure(AbstractAction.ActionError error) {
                    }
                },
                new AbstractAction.UICallBack<Pagination<OrderRecord>>() {
                    public void onSuccess(Pagination<OrderRecord> newsList) {
                        mShopLoadedFromServer = true;
                        if (isDetached() || getActivity() == null) //DO NOT update the view if this fragment is detached from the activity.
                            return;
                        if (dealsListAdaptor == null) {
                            dealsListAdaptor = new DealRecordArrayAdapter(getActivity(), R.layout.view_list_item_dealhistory, newsList.getItems());
                            setAdapter(dealsListAdaptor);
                        } else {
                            if (newsList.getItems().isEmpty()) {
                                Toast.makeText(getActivity(), R.string.no_more_to_load, Toast.LENGTH_SHORT).show();
                            } else {
                                dealsListAdaptor.clear();
                                dealsListAdaptor.addMore(newsList.getItems());
                            }
                        }
                        afterLoadReturned();
                    }

                    public void onFailure(AbstractAction.ActionError error) {
                        loadShopFromDB();
                        afterLoadReturned();
                    }
                }
        );
	}
	
	private void loadShopFromDB(){
		mAsyncTaskCount ++;
		Log.d(tag, " loadShopFromDB(): tasks: " + mAsyncTaskCount);
		new ParallelTask<List<OrderRecord>>() {
			protected List<OrderRecord> doInBackground(Void... params) {
                return Collections.emptyList();
			}
			public void onPostExecute(List<OrderRecord> shopList){
				//The pager is viewing another page now.
				if(getActivity() == null)
					return;
                shopList = new ArrayList<OrderRecord>();
                shopList.add(new OrderRecord());
                shopList.add(new OrderRecord());
                shopList.add(new OrderRecord());
				shopList.add(new OrderRecord());
				shopList.add(new OrderRecord());
				shopList.add(new OrderRecord());
				shopList.add(new OrderRecord());
				shopList.add(new OrderRecord());

				if(dealsListAdaptor == null){
					dealsListAdaptor = new DealRecordArrayAdapter(getActivity(), R.layout.view_list_item_shop, shopList);
					mGetShopAction = new GetOrderHistoryAction(getActivity(),  shopList.size(), Constants.PAGE_SIZE);
					setAdapter(dealsListAdaptor);
				}else{
					dealsListAdaptor.clear();
					dealsListAdaptor.addMore(shopList);
				}
				afterLoadReturned();
			}
		}.execute();
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		mAsyncTaskCount ++;
		Log.d(tag, " onPullUpToRefresh(): tasks: " + mAsyncTaskCount);
		mGetShopAction = (GetOrderHistoryAction)mGetShopAction.getNextPageAction();
		mGetShopAction.execute(
				new AbstractAction.BackgroundCallBack<Pagination<OrderRecord>>() {
					public void onSuccess(Pagination<OrderRecord> newsPage) {
					}

					public void onFailure(AbstractAction.ActionError error) {
					}
				},
				new AbstractAction.UICallBack<Pagination<OrderRecord>>() {
					public void onSuccess(Pagination<OrderRecord> shopList) {
						if (isDetached() || getActivity() == null) //DO NOT update the view if this fragment is detached from the activity.
							return;
						if (dealsListAdaptor == null) {
							dealsListAdaptor = new DealRecordArrayAdapter(getActivity(), R.layout.view_list_item_shop, shopList.getItems());
							setAdapter(dealsListAdaptor);
						} else {
							if (shopList.getItems().isEmpty()) {
								Toast.makeText(getActivity(), R.string.no_more_to_load, Toast.LENGTH_SHORT).show();
							} else {
								dealsListAdaptor.addMore(shopList.getItems());
							}
						}
						afterLoadReturned();
					}

					public void onFailure(AbstractAction.ActionError error) {
						Toast.makeText(getActivity(), R.string.load_failed, Toast.LENGTH_SHORT).show();
						mGetShopAction = (GetOrderHistoryAction) mGetShopAction.getPreviousPageAction();
						afterLoadReturned();
					}
				}
		);
	}
	
	private void afterLoadReturned(){
		mAsyncTaskCount --;
		Log.d(tag,  " afterLoadReturned(): tasks: " + mAsyncTaskCount);
		if(mAsyncTaskCount == 0){
			showListView();
		}
		refreshComplete();
	}
	
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(tag,  " onSaveInstanceState");
		outState.putBoolean("mShopLoaded", mShopLoadedFromServer);
	}

	public static class DealRecordArrayAdapter extends PTRListAdapter<OrderRecord> {
        private LayoutInflater mInflater;
        public DealRecordArrayAdapter(Context context, int res, List<OrderRecord> items) {
            super(context, res, items);
            mInflater = LayoutInflater.from(context);
        }

		public View getView(final int position, View convertView,
                ViewGroup parent) {
        	final OrderRecord shop = getItem(position);
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate( R.layout.view_list_item_shop, parent, false);
                holder = new ViewHolder();
//                holder.newsThumbnail = (CustomizeImageView) convertView.findViewById(R.id.id_shop_thumbnail);
                holder.newsTitle = (TextView) convertView.findViewById(R.id.id_shop_title);
                holder.newsVideoSign = convertView.findViewById(R.id.id_nearby_item_address);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
//        	CustomizeImageView newsThumbnail;
            TextView  newsTitle;
            View newsVideoSign;
            View newsSpecialSign;
        }
    }
	

	public void onResume(){
		Log.d(tag, " onResume()");
		super.onResume();
	}
	
	public void onViewStateRestored (Bundle savedInstanceState){
		Log.d(tag, " onViewStateRestored()");
		super.onViewStateRestored(savedInstanceState);
	}
	public void onStart (){
		Log.d(tag, " onStart()");
		super.onStart();
	}
	
	public void onPause (){
		Log.d(tag, " onPause()");
		super.onPause();
	}
	public void onStop (){
		Log.d(tag, " onStop()");
		super.onStop();
	}
	public void onDestroyView (){
		Log.d(tag, " onDestroyView()");
		super.onDestroyView();
	}
	public void onDestroy (){
		Log.d(tag, " onDestroy()");
		super.onDestroy();
	}
	public void onDetach (){
		Log.d(tag, " onDetach()");
		super.onDetach();
	}

	public void setMode(PullToRefreshBase.Mode mode){
		if(mode != null)
			mRefreshMode = mode;
	}

	public void setAdapter(ListAdapter adapter){
		mListAdapter = adapter;
		mListView.setAdapter(adapter);
	}
	protected final void showLoadingView() {
		mLoadingView.setVisibility(View.VISIBLE);
		mListView.setVisibility(View.GONE);
	}

	protected final void showListView() {
		mLoadingView.setVisibility(View.GONE);
		mListView.setVisibility(View.VISIBLE);
	}

	protected final void refreshComplete(){
		mListView.onRefreshComplete();
	}
}