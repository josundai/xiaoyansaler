package com.shangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class FragmentBalance extends Fragment {
    private static final String TAG = "XYK-FragmentBalance";

    private TextView mAmounts;
    private Button mRechargeHistory;
    private Button mRecharge;
    private TextView mMyPoints;
    private TextView mMyIngots;

    public FragmentBalance() {
        Log.d(TAG, "FragmentBalance()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_setting, container, false);
        /*
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_balance, container, false);

        HeaderView headerView = (HeaderView) view.findViewById(R.id.header);
        headerView.hideRightImage();
        headerView.setTitle(R.string.balance);
        headerView.setTitleCenter();

        mAmounts = (TextView)view.findViewById(R.id.id_text_amount);
        mRechargeHistory = (Button)view.findViewById(R.id.id_btn_recharge_history);
        mRecharge = (Button)view.findViewById(R.id.id_btn_recharge);
        mMyPoints = (TextView)view.findViewById(R.id.id_text_my_points);
        mMyIngots = (TextView)view.findViewById(R.id.id_text_my_ingots);
        addListeners();
        return view;
        */
    }

    private void addListeners(){
        /*
        mRechargeHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mRecharge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent);
            }
        });
        mMyPoints.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        mMyIngots.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}
