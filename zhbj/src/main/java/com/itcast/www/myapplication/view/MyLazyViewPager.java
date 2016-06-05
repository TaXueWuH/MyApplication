package com.itcast.www.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by LSL on 2016/6/5.
 */
public class MyLazyViewPager extends LazyViewPager {
    public MyLazyViewPager(Context context) {
        super(context);
    }
    public MyLazyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
