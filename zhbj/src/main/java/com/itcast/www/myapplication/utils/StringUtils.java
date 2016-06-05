package com.itcast.www.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LSL on 2016/6/5.
 */
public class StringUtils {
    /**
     * 根据key获取value
     * @param context
     * @param url
     * @return
     */
    public static String getSp(Context context, String url){
        SharedPreferences sp = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        return sp.getString(url,"");
    }

    public static Void setSp(Context context,String url,String values){
        SharedPreferences sp = context.getSharedPreferences("cache", Context.MODE_PRIVATE);
        sp.edit().putString(url,values).commit();
        return null;
    }
}
