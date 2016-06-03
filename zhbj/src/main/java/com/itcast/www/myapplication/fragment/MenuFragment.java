package com.itcast.www.myapplication.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by LSL on 2016/5/22.
 */
public class MenuFragment extends BaseFragment{

    private TextView textView;

    @Override
    public View initView() {

        textView= new TextView(context);

        return textView;
    }

    @Override
    public Void initData() {
        textView.setText("我是菜单界面");
        return null;
    }

}
