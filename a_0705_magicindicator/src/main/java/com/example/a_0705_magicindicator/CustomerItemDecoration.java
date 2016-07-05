package com.example.a_0705_magicindicator;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/7/5.
 */
public class CustomerItemDecoration extends RecyclerView.ItemDecoration {

    private int mMargin;
    public CustomerItemDecoration(Context context){
        super();
        mMargin = context.getResources().getDimensionPixelOffset(R.dimen.item_margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.set(mMargin,mMargin,mMargin,mMargin);
    }
}
