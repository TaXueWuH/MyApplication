package com.itcast.www.myapplication.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by LSL on 2016/6/4.
 */
public abstract class BasePager {
    public Context context;

    public BasePager(Context context){
            this.context = context;
    }

    public abstract View initView();

    public abstract void initData();
}
