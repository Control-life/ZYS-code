package com.example.a_0705_recyclerviewdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.ItemHolder> {

	interface OnItemClickListener{
		void onItemClick(ItemHolder item, int position);
	}
	
	private static final String [] ITEMS = {"A","B","C","D","E","F","G","H","I","J","K"};
	private List<String> mItems;
	
	private OnItemClickListener mOnItemClickListener;
	private LayoutInflater mLayoutInflater;
	
	public SimpleItemAdapter(Context context){
		mLayoutInflater = LayoutInflater.from(context);
		mItems = new ArrayList<>();
		mItems.addAll(Arrays.asList(ITEMS));
	}

	@Override
	public int getItemCount() {
		return mItems.size();
	}

	@Override
	public void onBindViewHolder(ItemHolder itemHolder, int position) {
		itemHolder.setmTitleView("Item #" + (position + 1));
		itemHolder.setmSummaryView(mItems.get(position));
	}

	@Override
	public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View itemView = mLayoutInflater.inflate(R.layout.collection_item, viewGroup,false);
		return new ItemHolder(itemView, this);
	}
	
	public OnItemClickListener getmOnItemClickListener() {
		return mOnItemClickListener;
	}

	public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	/**
	 * 在指定的位置添加一个条目
	 * @param item
	 * @param position
     */
	public void insertItemAtIndex(String item,int position){
		
		mItems.add(position,item);
		notifyItemInserted(position);
	}

	/**
	 * 删除指定位置的item
	 * @param position
     */
	public void removeItemAtPosition(int position){
		
		if(position >= mItems.size()){
			return ;
		}
		
		mItems.remove(position);
		notifyItemRemoved(position);
	}

	public static class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		private SimpleItemAdapter mParent;
		private TextView mTitleView,mSummaryView;

		public ItemHolder(View itemView,SimpleItemAdapter parent) {
			super(itemView);
			
			itemView.setOnClickListener(this);
			mParent = parent;
			
			mTitleView = (TextView) itemView.findViewById(R.id.text_title);
			mSummaryView = (TextView) itemView.findViewById(R.id.text_summary);
		}
		

		public void setmTitleView(CharSequence title) {
			this.mTitleView.setText(title);
		}



		public CharSequence getmSummaryView() {
			return mSummaryView.getText();
		}


		public void setmSummaryView(CharSequence summary) {
			this.mSummaryView.setText(summary);
		}



		@Override
		public void onClick(View v) {
			
			OnItemClickListener listener = mParent.getmOnItemClickListener();
			if(listener != null){
				listener.onItemClick(this, getPosition());
			}
		}
		
	}

}
