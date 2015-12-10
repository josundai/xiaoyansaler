package com.shangjia.model;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("ParcelCreator")
public class DealRecord extends BaseModel{

    public static DealRecord fromJSON(JSONObject json) throws JSONException {
        if(json == null)
            throw new IllegalArgumentException("JSONObject is null");
        //TODO: parse the deal record from JSON.
        DealRecord dealRecord = new DealRecord();
        return dealRecord;
    }
}
