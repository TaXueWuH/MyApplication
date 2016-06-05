package com.itcast.www.myapplication.fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.itcast.www.myapplication.bean.NewsCenterBean;

import java.util.List;

/**
 * Created by LSL on 2016/5/22.
 */
public class MenuFragment extends BaseFragment{

    private List<NewsCenterBean.DataBean> data;
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

    public void initSubMenu(List<NewsCenterBean.DataBean> data) {
        this.data = data;
        Log.i("MenuFragment","获取传递过来的数据"+data.toString());
    }
}
