package com.shangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.Arrays;


public class FragmentHome extends Fragment {
    private static final String TAG = "XYK-FragmentHome";
//	private PhotoPager mViewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_setting, container, false);
	}



    private void addListener(View homeView){
        /*
        homeView.findViewById(R.id.id_btn_my_coupons).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCouponsActivity.class);
                startActivity(intent);
            }
        });
        homeView.findViewById(R.id.id_btn_pay).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BarcodeActivity.class);
                startActivity(intent);
            }
        });
        homeView.findViewById(R.id.id_btn_recharge).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RechargeActivity.class);
                startActivity(intent);
            }
        });
        homeView.findViewById(R.id.id_btn_get_coupon).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SnapupCouponsActivity.class);
                startActivity(intent);
            }
        });
        homeView.findViewById(R.id.id_btn_activity).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LatestActivitiesActivity.class);
                startActivity(intent);
            }
        });
        homeView.findViewById(R.id.id_btn_try_lucky).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TryLuckyActivity.class);
                startActivity(intent);
            }
        });
        homeView.findViewById(R.id.id_btn_try_points_mall).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        homeView.findViewById(R.id.id_btn_ingots).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        homeView.findViewById(R.id.id_btn_property).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        homeView.findViewById(R.id.id_btn_my_custom_service).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        */
    }


    public void onResume() {
        super.onResume();
        Log.d(TAG, "onAttach(Activity)");
    }
}