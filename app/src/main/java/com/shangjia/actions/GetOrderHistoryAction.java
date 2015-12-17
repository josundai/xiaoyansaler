package com.shangjia.actions;

import android.content.Context;

import com.shangjia.model.OrderRecord;

import org.json.JSONException;
import org.json.JSONObject;

public class GetOrderHistoryAction extends PaginationAction<OrderRecord>{
    public GetOrderHistoryAction(Context context, int pageIndex, int pageSize) {
        super(context, pageIndex, pageSize);
        mServiceId = SERVICE_ID_CATEGORY;
    }

    public void addRequestParameters(JSONObject parameters) throws JSONException {
        super.addRequestParameters(parameters);
    }

//    @Override
//    public PaginationAction<OrderRecord> cloneCurrentPageAction() {
//        return new GetOrderHistoryAction(mAppContext, getPageIndex(), getPageSize());
//    }

    public GetOrderHistoryAction cloneCurrentPageAction(){
        GetOrderHistoryAction action = new GetOrderHistoryAction(
                mAppContext,
                getPageIndex(),
                getPageSize()
        );
        return action;
    }

    @Override
    public OrderRecord convertJsonToResult(JSONObject item) throws JSONException {
        return OrderRecord.fromJSON(item);
    }

}