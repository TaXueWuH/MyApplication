package com.itcast.www.myapplication.pager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.itcast.www.myapplication.MainActivity;
import com.itcast.www.myapplication.bean.NewsCenterBean;
import com.itcast.www.myapplication.fragment.MenuFragment;
import com.itcast.www.myapplication.utils.NetUrl;
import com.itcast.www.myapplication.utils.StringUtils;
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
        //先从本地读取，读取不到，在从网络获取
        String jsonString = StringUtils.getSp(context, NetUrl.NEWS_CENTER_CATEGORIES);
        if(!TextUtils.isEmpty(jsonString)){
//            parseJson(jsonString);
        }
        requestData();
    }

    private void requestData() {
        sendRequest(NetUrl.NEWS_CENTER_CATEGORIES,new RequestCallBack<String>() {
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

    /**
     * 抽取自定义的网络请求
     * @param url
     * @param requestCallBack
     */
    private void sendRequest(String url,RequestCallBack<String> requestCallBack) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url,  null,requestCallBack);
    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        NewsCenterBean newsCenterBean = gson.fromJson(result, NewsCenterBean.class);

        //将解析出来的数据传递给MenuFragment界面
        MainActivity mainActivity = (MainActivity) context;
        MenuFragment menuFragment = mainActivity.getMenuFragmentByTag();
        menuFragment.initSubMenu(newsCenterBean.data);
    }
}
