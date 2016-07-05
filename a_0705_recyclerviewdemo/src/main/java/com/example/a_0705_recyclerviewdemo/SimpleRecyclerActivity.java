package com.example.a_0705_recyclerviewdemo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SimpleRecyclerActivity extends Activity implements SimpleItemAdapter.OnItemClickListener {

	private RecyclerView mRecyclerView;
	private SimpleItemAdapter mAdapter;
	/**
	 * 布局管理器
	 */
	private LinearLayoutManager mHorizontalManager;
	private LinearLayoutManager mVerticalManager;
	private GridLayoutManager mHorizontalGridManager;
	private GridLayoutManager mVerticalGridManager;
	
	/** 修饰 */
	private ConnectorDecoration mConnectors;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mRecyclerView = new RecyclerView(this);

		//初始化布局管理器
		mHorizontalManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
		mVerticalManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		
		mHorizontalGridManager = new GridLayoutManager(this, 3, LinearLayoutManager.HORIZONTAL, false);
		mVerticalGridManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
		
		//垂直网格的连接线修饰
		mConnectors = new ConnectorDecoration(this);
		
		//交错垂直网格
		mVerticalGridManager.setSpanSizeLookup(new GridStaggerLookup());
		
		mAdapter = new SimpleItemAdapter(this);
		mAdapter.setmOnItemClickListener(this);
		mRecyclerView.setAdapter(mAdapter);
		
		//对所有链接应用边缘修饰
		mRecyclerView.addItemDecoration(new InsetDecoration(this));
		
		//默认为垂直布局
		selectLayoutManager(R.id.action_vertical);
		setContentView(mRecyclerView);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.layout_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
		
		return selectLayoutManager(item.getItemId());
	}

	private boolean selectLayoutManager(int itemId) {
		
		switch (itemId) {
		case R.id.action_add_item:
			//插入新的项目
			mAdapter.insertItemAtIndex("android recipes", 0);
			return true;
		case R.id.action_remove_item:
			//删除第一项
			mAdapter.removeItemAtPosition(1);
			return true;
		case R.id.action_grid_horizontal:
			mRecyclerView.setLayoutManager(mHorizontalGridManager);
			mRecyclerView.removeItemDecoration(mConnectors);
			return true;
		case R.id.action_grid_vertical:
			mRecyclerView.setLayoutManager(mVerticalGridManager);
			mRecyclerView.addItemDecoration(mConnectors);
			return true;
		case R.id.action_horizontal:
			mRecyclerView.setLayoutManager(mHorizontalManager);
			mRecyclerView.removeItemDecoration(mConnectors);
			return true;
		case R.id.action_vertical:
			mRecyclerView.setLayoutManager(mVerticalManager);
			mRecyclerView.removeItemDecoration(mConnectors);
			return true;
		}
		
		return false;
	}

	@Override
	public void onItemClick(SimpleItemAdapter.ItemHolder item, int position) {
		
		Toast.makeText(this, item.getmSummaryView(), Toast.LENGTH_SHORT).show();
	}
	
}
