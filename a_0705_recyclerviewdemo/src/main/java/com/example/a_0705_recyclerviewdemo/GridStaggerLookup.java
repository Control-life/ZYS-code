package com.example.a_0705_recyclerviewdemo;

import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

/**
 * 交错网格 spanSizeLookup
 * @author Administrator
 *
 */
public class GridStaggerLookup extends GridLayoutManager.SpanSizeLookup {

	@Override
	public int getSpanSize(int position) {
		Log.e("GridStaggerLookup","position = " + position);
		return (position % 3 == 0 ? 2 : 1);
	}

}
