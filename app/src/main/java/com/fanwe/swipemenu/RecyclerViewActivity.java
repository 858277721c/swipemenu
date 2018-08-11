package com.fanwe.swipemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fanwe.lib.swipemenu.adapter.SwipeMenuRecyclerAdapter;

public class RecyclerViewActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter = new RecyclerViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter.getDataHolder().setData(DataModel.get(50));
        mRecyclerView.setAdapter(new SwipeMenuRecyclerAdapter(mAdapter));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                mAdapter.mAdapterSwipeMenuHolder.setAllSwipeMenuOpenedExcept(false, true, null);
            }
        });
    }
}
