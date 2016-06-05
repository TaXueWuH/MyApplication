package com.itcast.www.myapplication.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itcast.www.myapplication.bean.NewsCenterBean;
import com.itcast.www.myapplication.utils.NetUrl;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by LSL on 2016/6/4.
 */
public class NewsCenterPage extends BasePager {

    private TextView textView;

    public NewsCenterPage(Context context){
        super(context);
    }


    @Override
    public View initView() {
        textView =  new TextView(context);

        return textView;

    }

    @Override
    public void initData() {
        textView.setText("我是新闻中心");

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,NetUrl.NEWS_CENTER_CATEGORIES,  null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("NewsCenterPage",result);
                parseJson(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                Log.i("NewsCenterPage","请求失败"+msg);
            }
        });
    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        NewsCenterBean newsCenterBean = gson.fromJson(result, NewsCenterBean.class);
                Log.i("NewsCenterPage","解析成功"+newsCenterBean.data.get(0).title);
    }
}
