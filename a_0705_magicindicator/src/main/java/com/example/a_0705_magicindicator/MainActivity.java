package com.example.a_0705_magicindicator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView mRecycerView;
    private List<String> mDatas;
    private String[] titles = {"Indicator1", "Indicator2", "Indicator3"
            , "Indicator4", "Indicator5", "Indicator6"
            , "Indicator7", "Indicator8", "Indicator9"
            , "Indicator10", "Indicator11", "Indicator12", "Indicator13"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecycerView = new RecyclerView(this);
        initData();

        mRecycerView.setAdapter(new CustomerRecyclerAdapter(mDatas,this));
        mRecycerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecycerView.addItemDecoration(new CustomerItemDecoration(this));
        setContentView(mRecycerView);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.addAll(Arrays.asList(titles));
    }


}
