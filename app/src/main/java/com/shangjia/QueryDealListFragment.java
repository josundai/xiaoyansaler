package com.shangjia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.shangjia.actions.AbstractAction;
import com.shangjia.actions.GetDealHistoryAction;
import com.shangjia.actions.ParallelTask;
import com.shangjia.model.DealRecord;
import com.shangjia.model.Pagination;
import com.shangjia.views.ptr.PTRListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryDealListFragment extends Fragment  implements PullToRefreshBase.OnRefreshListener2<ListView> {
	private final static String tag = "TT-FragNearbyShopList";

	private String mCategory;
	private PTRListAdapter<DealRecord> mShopListAdapter;
	private List<DealRecord> mShopList;
	private boolean mShopLoadedFromServer = false;
	private GetDealHistoryAction mGetShopAction;

//	private CustomizedPTRListView mListView;
	private View mLoadingView;
	private ListAdapter mListAdapter;
	private PullToRefreshBase.Mode mRefreshMode = PullToRefreshBase.Mode.BOTH;

	private int mAsyncTaskCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(tag, mCategory + " onCreate()");
		super.onCreate(savedInstanceState);
        mCategory = getArguments().getString("category");
		if (savedInstanceState != null) {
			mCategory = savedInstanceState.getString("mCategory");
			mShopLoadedFromServer = savedInstanceState.getBoolean("mShopLoaded");
		}
//		setMode(PullToRefreshBase.Mode.PULL_FROM_END);
//		mNewsDAO = ((TouTiaoApp)getActivity().getApplication()).getNewsDAO();
//		mHeadNewsDAO = ((TouTiaoApp)getActivity().getApplication()).getHeadNewsDAO();
	}
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Log.d(tag, mCategory + " onActivityCreated()");
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
		Log.d(tag, mCategory + " loadShopFromServer(): tasks: " + mAsyncTaskCount);
		mGetShopAction = new GetDealHistoryAction(getActivity(),  1, Constants.PAGE_SIZE);
		mGetShopAction.execute(
                new AbstractAction.BackgroundCallBack<Pagination<DealRecord>>() {
                    public void onSuccess(Pagination<DealRecord> newsPage) {
                    }

                    public void onFailure(AbstractAction.ActionError error) {
                    }
                },
                new AbstractAction.UICallBack<Pagination<DealRecord>>() {
                    public void onSuccess(Pagination<DealRecord> newsList) {
                        mShopLoadedFromServer = true;
                        if (isDetached() || getActivity() == null) //DO NOT update the view if this fragment is detached from the activity.
                            return;
                        if (mShopListAdapter == null) {
                            mShopListAdapter = new ShopArrayAdapter(getActivity(), R.layout.view_list_item_dealhistory, newsList.getItems());
                            setAdapter(mShopListAdapter);
                        } else {
                            if (newsList.getItems().isEmpty()) {
                                Toast.makeText(getActivity(), R.string.no_more_to_load, Toast.LENGTH_SHORT).show();
                            } else {
                                mShopListAdapter.clear();
                                mShopListAdapter.addMore(newsList.getItems());
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
		Log.d(tag, mCategory +" loadShopFromDB(): tasks: " + mAsyncTaskCount);
		new ParallelTask<List<DealRecord>>() {
			protected List<DealRecord> doInBackground(Void... params) {
                return Collections.emptyList();
			}
			public void onPostExecute(List<DealRecord> shopList){
				//The pager is viewing another page now.
				if(getActivity() == null)
					return;
                shopList = new ArrayList<DealRecord>();
                shopList.add(new DealRecord());
                shopList.add(new DealRecord());
                shopList.add(new DealRecord());
				shopList.add(new DealRecord());
				shopList.add(new DealRecord());
				shopList.add(new DealRecord());
				shopList.add(new DealRecord());
				shopList.add(new DealRecord());

				if(mShopListAdapter == null){
					mShopListAdapter = new ShopArrayAdapter(getActivity(), R.layout.view_list_item_shop, shopList);
					mGetShopAction = new GetDealHistoryAction(getActivity(),  shopList.size(), Constants.PAGE_SIZE);
					setAdapter(mShopListAdapter);
				}else{
					mShopListAdapter.clear();
					mShopListAdapter.addMore(shopList);
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
		Log.d(tag, mCategory +" onPullUpToRefresh(): tasks: " + mAsyncTaskCount);
		mGetShopAction = (GetDealHistoryAction)mGetShopAction.getNextPageAction();
		mGetShopAction.execute(
				new AbstractAction.BackgroundCallBack<Pagination<DealRecord>>() {
					public void onSuccess(Pagination<DealRecord> newsPage) {
					}

					public void onFailure(AbstractAction.ActionError error) {
					}
				},
				new AbstractAction.UICallBack<Pagination<DealRecord>>() {
					public void onSuccess(Pagination<DealRecord> shopList) {
						if (isDetached() || getActivity() == null) //DO NOT update the view if this fragment is detached from the activity.
							return;
						if (mShopListAdapter == null) {
							mShopListAdapter = new ShopArrayAdapter(getActivity(), R.layout.view_list_item_shop, shopList.getItems());
							setAdapter(mShopListAdapter);
						} else {
							if (shopList.getItems().isEmpty()) {
								Toast.makeText(getActivity(), R.string.no_more_to_load, Toast.LENGTH_SHORT).show();
							} else {
								mShopListAdapter.addMore(shopList.getItems());
							}
						}
						afterLoadReturned();
					}

					public void onFailure(AbstractAction.ActionError error) {
						Toast.makeText(getActivity(), R.string.load_failed, Toast.LENGTH_SHORT).show();
						mGetShopAction = (GetDealHistoryAction) mGetShopAction.getPreviousPageAction();
						afterLoadReturned();
					}
				}
		);
	}
	
	private void afterLoadReturned(){
		mAsyncTaskCount --;
		Log.d(tag, mCategory + " afterLoadReturned(): tasks: " + mAsyncTaskCount);
		if(mAsyncTaskCount == 0){
			showListView();
		}
		refreshComplete();
	}
	
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(tag, mCategory + " onSaveInstanceState");
		outState.putString("mCategory", mCategory);
		outState.putBoolean("mShopLoaded", mShopLoadedFromServer);
	}

	public RecyclerView.ViewHolder createHeaderView(LayoutInflater inflater){
		Log.d(tag, mCategory + " createHeaderView");
		return null;
	}

	private static void showShop(Context context, DealRecord dealRecord){
		Intent showNewsDetailIntent = new Intent();
		context.startActivity(showNewsDetailIntent);
	}
	
	public static class ShopArrayAdapter extends PTRListAdapter<DealRecord> {
        private LayoutInflater mInflater;
        public ShopArrayAdapter(Context context, int res, List<DealRecord> items) {
            super(context, res, items);
            mInflater = LayoutInflater.from(context);
        }

		public View getView(final int position, View convertView,
                ViewGroup parent) {
        	final DealRecord shop = getItem(position);
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
//            if(!TextUtils.isEmpty(shop.getName())){
//                holder.newsTitle.setText(shop.getName());
//            }
//            if( shop.getThumbnailUrls().size() > 0){
//        		holder.newsThumbnail.loadImage(news.getThumbnailUrls().get(0));
//            }
            

            convertView.setOnClickListener(new OnClickListener(){
				public void onClick(View v) {
					showShop(getContext(), shop);
				}
            });
            
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
		Log.d(tag, mCategory +" onResume()");
		super.onResume();
	}
	
	public void onViewStateRestored (Bundle savedInstanceState){
		Log.d(tag, mCategory +" onViewStateRestored()");
		super.onViewStateRestored(savedInstanceState);
	}
	public void onStart (){
		Log.d(tag, mCategory +" onStart()");
		super.onStart();
	}
	
	public void onPause (){
		Log.d(tag, mCategory +" onPause()");
		super.onPause();
	}
	public void onStop (){
		Log.d(tag, mCategory +" onStop()");
		super.onStop();
	}
	public void onDestroyView (){
		Log.d(tag, mCategory +" onDestroyView()");
		super.onDestroyView();
	}
	public void onDestroy (){
		Log.d(tag, mCategory +" onDestroy()");
		super.onDestroy();
	}
	public void onDetach (){
		Log.d(tag, mCategory +" onDetach()");
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
}