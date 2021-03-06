package com.taobao.daogou.client.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by jian on 14-8-9.
 * 具有分段加载功能的GridView
 */
public class GridViewWithPagerLoader extends GridView implements AbsListView.OnScrollListener {

    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;
    private DataLoader dataLoader;

    public GridViewWithPagerLoader(Context context) {
        super(context);
        setOnScrollListener(this);
    }

    public GridViewWithPagerLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public GridViewWithPagerLoader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollListener(this);
    }

    public void setDataLoader(DataLoader d) {
        dataLoader = d;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        int itemsLastIndex = dataLoader.getCount() - 1;    //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 0;             //可以加上底部的loadMoreView项
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            dataLoader.loadData();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }
}
