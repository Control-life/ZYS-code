package com.example.a_0705_recyclerviewdemo;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/**
 * 提供嵌入式页边距的ItemDecoration
 * @author Administrator
 *
 */
public class InsetDecoration extends RecyclerView.ItemDecoration {

	private int mInsetMargin;
	
	public InsetDecoration(Context context){
		super();
		mInsetMargin = context.getResources().getDimensionPixelOffset(R.dimen.inset_margin);
	}
	
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
		outRect.set(mInsetMargin, mInsetMargin, mInsetMargin, mInsetMargin);
	}
}
