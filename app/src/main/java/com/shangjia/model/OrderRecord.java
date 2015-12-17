package com.shangjia.model;

import android.annotation.SuppressLint;
import android.os.Parcel;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("ParcelCreator")
public class OrderRecord extends BaseModel{

    private String id;
    private String status;
    private String time;
    private String number;

    public static final Creator<OrderRecord> CREATOR
            = new Creator<OrderRecord>() {
        public OrderRecord createFromParcel(Parcel in) {
            return new OrderRecord(in);
        }

        public OrderRecord[] newArray(int size) {
            return new OrderRecord[size];
        }
    };


    public static OrderRecord fromJSON(JSONObject json) throws JSONException {
        if(json == null)
            throw new IllegalArgumentException("JSONObject is null");
        //TODO: parse the deal record from JSON.
        OrderRecord orderRecord = new OrderRecord();
        return orderRecord;
    }

    public OrderRecord(){

    }

    public OrderRecord(Parcel in){
        super(in);
        id = in.readString();
        status = in.readString();
        time = in.readString();
        number = in.readString();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
