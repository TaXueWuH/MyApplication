package com.itcast.www.myapplication.sub_pages;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.itcast.www.myapplication.bean.NewsCenterBean;
import com.itcast.www.myapplication.pager.BasePager;

/**
 * Created by LSL on 2016/6/6.
 */
public class NewsItemPager extends BasePager {

    public NewsCenterBean.DataBean.ChildrenBean childenBean;
    private TextView textView;

    public NewsItemPager(Context context,NewsCenterBean.DataBean.ChildrenBean childrenBean) {
        super(context);
        this.childenBean = childrenBean;
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        return textView;
    }

    @Override
    public void initData() {
        textView.setText(childenBean.title);
    }
}
