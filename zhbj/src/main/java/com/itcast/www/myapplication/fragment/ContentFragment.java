package com.itcast.www.myapplication.fragment;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.itcast.www.myapplication.R;
import com.itcast.www.myapplication.pager.BasePager;
import com.itcast.www.myapplication.pager.GovPage;
import com.itcast.www.myapplication.pager.HomePage;
import com.itcast.www.myapplication.pager.NewsCenterPage;
import com.itcast.www.myapplication.pager.SettingPage;
import com.itcast.www.myapplication.pager.SmartSevicePage;
import com.itcast.www.myapplication.view.MyLazyViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSL on 2016/5/22.
 */
public class ContentFragment extends BaseFragment{

    private final class OnMyCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

            int item = 0;
            switch(checkId){

                case R.id.rb_home:
                    item = 0;
                    break;
                case R.id.rb_news_center:
                    item = 1;
                    break;
                case R.id.rb_smart_service:
                    item = 2;
                    break;
                case R.id.rb_gov_affairs:
                    item = 3;
                    break;
                case R.id.rb_setting:
                    item= 4;
                    break;
                default:
                    break;
            }
            vp_home.setCurrentItem(item);

        }
    }

    @ViewInject(R.id.vp_home)
    private MyLazyViewPager vp_home;
    @ViewInject(R.id.rg_home)
    private RadioGroup rg_home;

    @Override
    public View initView() {


        View view = View.inflate(context, R.layout.fragment_home,null);
        ViewUtils.inject(this,view);

        rg_home.setOnCheckedChangeListener(new OnMyCheckedChangeListener());
        rg_home.check(R.id.rb_home);
        return view;
    }

    private List<BasePager> pages = new ArrayList<>();

    @Override
    public Void initData() {
        pages.add(new HomePage(context));
        pages.add(new NewsCenterPage(context));
        pages.add(new SmartSevicePage(context));
        pages.add(new GovPage(context));
        pages.add(new SettingPage(context));

        MyPagerAdapter  adapter= new MyPagerAdapter();
        vp_home.setAdapter(adapter);
        return null;
    }

    /**
     * 将数据暴露出去
     * @return
     */
    public List<BasePager> getPages(){
            return pages;
    }

    class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager currentPage = pages.get(position);
            View currentView =  currentPage.initView();
            currentPage.initData();
            container.addView(currentView);
            return currentView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
