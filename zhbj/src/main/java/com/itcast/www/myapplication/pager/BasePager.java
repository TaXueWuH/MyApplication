package com.itcast.www.myapplication.pager;

import android.content.Context;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

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

    /**
     * 抽取自定义的网络请求
     *
     * @param url
     * @param requestCallBack
     */
    public void sendRequest(String url, RequestCallBack<String> requestCallBack) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, null, requestCallBack);
    }
}
