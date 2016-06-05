package com.itcast.www.myapplication.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by LSL on 2016/6/4.
 */
public class HomePage extends BasePager {

    private TextView textView;

    public HomePage(Context context){
        super(context);
    }


    @Override
    public View initView() {
        textView =  new TextView(context);

        return textView;

    }

    @Override
    public void initData() {
        textView.setText("我是首页");
    }
}
