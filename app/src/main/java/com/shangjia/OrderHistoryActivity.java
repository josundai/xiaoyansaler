package com.shangjia;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.shangjia.actions.AbstractAction;
import com.shangjia.actions.GetOrderHistoryAction;
import com.shangjia.model.OrderRecord;
import com.shangjia.model.Pagination;
import com.shangjia.views.HeaderView;
import com.shangjia.views.ptr.LoadingSupportPTRListView;
import com.shangjia.views.ptr.PTRListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dai on 2015/12/16.
 */
public class OrderHistoryActivity extends ListActivity implements PullToRefreshBase.OnRefreshListener2<ListView> {

    private LoadingSupportPTRListView mLoadingSupportPTRListView;
    private RechargeListAdapter mCouponsListAdapter;
    private GetOrderHistoryAction mGetOrderPointAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple_ptr_list);

        HeaderView headerView = (HeaderView) findViewById(R.id.header);
        headerView.hideRightImage();
        headerView.setTitle(R.string.shop_history);
//        headerView.setTitleCenter();
        mLoadingSupportPTRListView = (LoadingSupportPTRListView)findViewById(R.id.refresh_widget);
        mLoadingSupportPTRListView.setOnRefreshListener(this);
        mLoadingSupportPTRListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        loadFirstPage(true);
    }

    public class RechargeListAdapter extends PTRListAdapter<OrderRecord> {
        private LayoutInflater mInflater;

        public RechargeListAdapter(Context context, int resource, List<OrderRecord> objects) {
            super(context, resource, objects);
            mInflater = LayoutInflater.from(context);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            final OrderRecord coupon = getItem(position);
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.view_list_item_order_history, parent, false);
                holder = new ViewHolder();
                holder.amount = (TextView) convertView.findViewById(R.id.id_banlance_recharge_amount);
                holder.time = (TextView) convertView.findViewById(R.id.id_banlance_recharge_time);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder {
            TextView amount;
            TextView time;
        }
    }


    private void loadFirstPage(boolean isInitialLoad) {
        if (isInitialLoad)
            mLoadingSupportPTRListView.showLoadingView();
        mGetOrderPointAction = new GetOrderHistoryAction(this, 1, 10);
        mGetOrderPointAction.execute(
                new AbstractAction.BackgroundCallBack<Pagination<OrderRecord>>() {
                    public void onSuccess(Pagination<OrderRecord> result) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }

                    public void onFailure(AbstractAction.ActionError error) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                },
                new AbstractAction.UICallBack<Pagination<OrderRecord>>() {
                    public void onSuccess(Pagination<OrderRecord> result) {
                        List<OrderRecord> coupons = new ArrayList<>();
                        coupons.add(new OrderRecord());
                        coupons.add(new OrderRecord());
                        coupons.add(new OrderRecord());
                        mCouponsListAdapter = new RechargeListAdapter(getBaseContext(), R.layout.view_list_item_order_history, coupons);
                        setListAdapter(mCouponsListAdapter);
                        getListView().setDivider(null);
                        mLoadingSupportPTRListView.showListView();
                        mLoadingSupportPTRListView.refreshComplete();
                    }

                    public void onFailure(AbstractAction.ActionError error) {
                        mGetOrderPointAction = mGetOrderPointAction.cloneCurrentPageAction();
                        List<OrderRecord> coupons = new ArrayList<>();
                        coupons.add(new OrderRecord());
                        coupons.add(new OrderRecord());
                        coupons.add(new OrderRecord());
                        mCouponsListAdapter = new RechargeListAdapter(getBaseContext(), R.layout.view_list_item_order_history, coupons);
                        setListAdapter(mCouponsListAdapter);
                        getListView().setDivider(null);
                        mLoadingSupportPTRListView.showListView();
                        mLoadingSupportPTRListView.refreshComplete();
                    }
                }
        );
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        loadFirstPage(false);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

    }
}
