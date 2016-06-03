package com.itcast.www.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LSL on 2016/5/22.
 */
public abstract class BaseFragment  extends Fragment{

    public Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View view = initView();
        initData();
        return view;
    }

    public abstract View initView();
    public abstract Void initData();
}
