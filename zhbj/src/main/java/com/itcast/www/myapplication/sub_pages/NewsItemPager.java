package com.itcast.www.myapplication.sub_pages;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itcast.www.myapplication.bean.NewsCenterBean;
import com.itcast.www.myapplication.bean.NewsItemBean;
import com.itcast.www.myapplication.pager.BasePager;
import com.itcast.www.myapplication.utils.NetUrl;
import com.itcast.www.myapplication.utils.StringUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

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

        //1.先从缓存中读取数据
        String cacheJson = StringUtils.getSp(context, NetUrl.BASE_URL + childenBean.url);
        if(!TextUtils.isEmpty(cacheJson)){
//            parseJson(cacheJson);
        }

        sendRequest(NetUrl.BASE_URL + childenBean.url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String resultJson = responseInfo.result;
                parseJson(resultJson);
//                Log.i("NewsItemPages","解析成功："+resultJson);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                error.printStackTrace();
                Log.i("NewsItemPages","新闻中心子界面请求数据失败"+msg);
            }
        });

    }

    private void parseJson(String resultJson) {

        Gson gson = new Gson();
        NewsItemBean newsItemBean = gson.fromJson(resultJson, NewsItemBean.class);
        Log.i("NewsItemPager",newsItemBean.getData().getNews().get(0).getTitle());

    }
}
