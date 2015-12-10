package com.shangjia.views.ptr;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Collection;
import java.util.List;

public class PTRListAdapter<T> extends ArrayAdapter<T>{
	private List<T> mObjects;
	public PTRListAdapter(Context context, int resource, List<T> objects) {
		super(context, resource, objects);
		mObjects = objects;
	}
	
	/**
	 * Override this because this is not available in SDK lower than 11;
	 */
    public void addMore(Collection<? extends T> collection) {
        mObjects.addAll(collection);
        notifyDataSetChanged();
    }

	/**
	 * Override this because this is not available in SDK lower than 11;
	 */
	public void addMoreToTop(Collection<? extends T> collection) {
        T[] newItems = (T[])collection.toArray();
        for(int i=0; i<newItems.length; i++) {
            mObjects.add(i, newItems[i]);
        }
		notifyDataSetChanged();
	}
}
