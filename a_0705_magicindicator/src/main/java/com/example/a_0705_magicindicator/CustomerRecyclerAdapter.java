package com.example.a_0705_magicindicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class CustomerRecyclerAdapter extends Adapter<CustomerRecyclerAdapter.RecyclerViewHolder> {

    private final Context context;
    /**
     * 点击监听事件
     */
    private OnItemClickListener mOnItemClickListener;
    private List<String> mDatas;

    /**
     * 单个条目的点击事件
     */
    interface OnItemClickListener {

        void onItemClick(RecyclerViewHolder holder, int position);
    }


    public void setMOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public OnItemClickListener getMOnItemClickListener() {
        return mOnItemClickListener;
    }

    public CustomerRecyclerAdapter(List<String> datas, Context context) {
        this.mDatas = datas;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Button button = new Button(context);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);

        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);
        button.setBackgroundColor(Color.parseColor("#654321"));
        button.setTextColor(Color.WHITE);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(button, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Button button = holder.getButton();
        button.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size() == 0 ? 0 : mDatas.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CustomerRecyclerAdapter mParent;
        private Button button;

        public RecyclerViewHolder(View itemView, CustomerRecyclerAdapter parent) {
            super(itemView);
            mParent = parent;
            if (itemView instanceof Button) {
                button = (Button) itemView;
            }
            itemView.setOnClickListener(this);
        }

        public Button getButton() {
            return button;
        }

        @Override
        public void onClick(View v) {

            OnItemClickListener itemClick = mParent.getMOnItemClickListener();
            if (itemClick != null) {
                itemClick.onItemClick(this, getAdapterPosition());
            }
        }
    }
}
