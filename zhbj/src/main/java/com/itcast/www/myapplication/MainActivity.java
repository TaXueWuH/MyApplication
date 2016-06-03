package com.itcast.www.myapplication;

import android.os.Bundle;

import com.itcast.www.myapplication.fragment.ContentFragment;
import com.itcast.www.myapplication.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slidemenu_content);
        setBehindContentView(R.layout.slidemenu_menu);

        SlidingMenu slidingMenu = getSlidingMenu();

        //设置打开方式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置位置
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置分割线
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);

        //设置偏移距离
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content,new ContentFragment(),"CONTENT").commit();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_menu,new MenuFragment(),"MENU").commit();
    }
}
