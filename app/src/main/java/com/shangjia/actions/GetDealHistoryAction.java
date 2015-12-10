package com.shangjia.actions;

import android.content.Context;

import com.shangjia.model.DealRecord;

import org.json.JSONException;
import org.json.JSONObject;

public class GetDealHistoryAction extends PaginationAction<DealRecord>{
    public GetDealHistoryAction(Context context, int pageIndex, int pageSize) {
        super(context, pageIndex, pageSize);
        mServiceId = SERVICE_ID_CATEGORY;
    }

    public void addRequestParameters(JSONObject parameters) throws JSONException {
        super.addRequestParameters(parameters);
    }

    @Override
    public PaginationAction<DealRecord> cloneCurrentPageAction() {
        return new GetDealHistoryAction(mAppContext, getPageIndex(), getPageSize());
    }

    @Override
    public DealRecord convertJsonToResult(JSONObject item) throws JSONException {
        return DealRecord.fromJSON(item);
    }

}