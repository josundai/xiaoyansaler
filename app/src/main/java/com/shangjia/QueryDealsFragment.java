package com.shangjia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shangjia.actions.AbstractAction;
import com.shangjia.actions.GetDealHistoryAction;
import com.shangjia.model.Pagination;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class QueryDealsFragment extends Fragment {
    private static final String TAG = "XYK-FragmentQueryDeals";

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private ViewPager mViewPager;
    private View mLoadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_querydeals, container, false);

        mLoadingView = view.findViewById(R.id.id_category_loading);
        mViewPager = (ViewPager) view.findViewById(R.id.nearbyViewpager);

        return view;
    }


    private void loadCategoryFromServer(){
        Log.d(TAG, "loadCategoryFromServer");
        mLoadingView.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.GONE);
        GetDealHistoryAction getCategoryAction = new GetDealHistoryAction(getActivity(), 1, 100);
        getCategoryAction.execute(
                new AbstractAction.BackgroundCallBack<Pagination<String>>() {
                    public void onSuccess(Pagination<String> result) {
//                        mcategoryDAO.delete();
//                        mcategoryDAO.save(result.getItems());
                    }
                },
                new AbstractAction.UICallBack<Pagination<String>>() {
                    public void onSuccess(Pagination<String> result) {
                        updateUI(result.getItems());
                    }

                    public void onFailure(AbstractAction.ActionError error) {
//                        loadCategoryFromDB();
                    }
                }
        );
    }


    private void updateUI(final List<String> categories){
        Log.d(TAG, "updateUI");
        mLoadingView.setVisibility(View.GONE);
        mViewPager.setVisibility(View.VISIBLE);

        //Pager
        mViewPager.setAdapter(new ShopPagerAdapter(getChildFragmentManager(), categories));
        //切换Pager时,要将当前对应的Tab滚动到可见位置
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
                int moveLeft = (int) mTabs.get(position).getLeft() - (int) mTabs.get(1).getLeft();
                mTabScrollView.smoothScrollTo(moveLeft, 0);
            }
        });
    }
    /**
     * A simple pager adapter that represents 5 {@link SlidePageFragment}
     * objects, in sequence.
     */
    private class ShopPagerAdapter extends FragmentStatePagerAdapter {
        public static final String tag = "XYK-ShopPagerAdapter";
        List<String> mCategories;
        public ShopPagerAdapter(FragmentManager fm, List<String> categories) {
            super(fm);
            mCategories = categories;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(tag, ": getItem: " + position);
            FragmentNearbyShopList fragment = new FragmentNearbyShopList();
            Bundle bundle = new Bundle();
            bundle.putString("category", mCategories.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mCategories.size();
        }
    }


    public void onResume(){
        Log.d(TAG, " onResume()");
        super.onResume();
    }

    public void onViewStateRestored (Bundle savedInstanceState){
        Log.d(TAG, " onViewStateRestored()");
        super.onViewStateRestored(savedInstanceState);
    }
    public void onStart (){
        Log.d(TAG, " onStart()");
        super.onStart();
    }

    public void onPause (){
        Log.d(TAG,  " onPause()");
        super.onPause();
    }
    public void onStop (){
        Log.d(TAG,  " onStop()");
        super.onStop();
    }
    public void onDestroyView (){
        Log.d(TAG, " onDestroyView()");
        super.onDestroyView();
    }
    public void onDestroy (){
        Log.d(TAG, " onDestroy()");
        super.onDestroy();
    }
    public void onDetach (){
        Log.d(TAG, " onDetach()");
        super.onDetach();
    }







}

