package com.itcast.www.myapplication.sub_pages;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itcast.www.myapplication.R;
import com.itcast.www.myapplication.bean.NewsCenterBean;
import com.itcast.www.myapplication.indicator.TabPageIndicator;
import com.itcast.www.myapplication.pager.BasePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSL on 2016/6/6.
 */
public class NewsPager extends BasePager {

    private final NewsCenterBean.DataBean children;
    @ViewInject(R.id.indicator)
    private TabPageIndicator indicator;
    @ViewInject(R.id.iv_edit_cate)
    private ImageView iv_edit_cate;
    @ViewInject(R.id.ll_indicator)
    private LinearLayout ll_indicator;
    @ViewInject(R.id.vp_sub_pager_news)
    private ViewPager vp_sub_pager_news;

    public NewsPager(Context context, NewsCenterBean.DataBean children) {
        super(context);
        this.children = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.sub_pager_news, null);
        ViewUtils.inject(this,view);
        return view;
    }

    private List<NewsItemPager> newsItemPages = new ArrayList<>();
    @Override
    public void initData() {
        //解决重复进入newsItemsPages重复添加，导致闪退的BUG
        newsItemPages.clear();
        for (int i = 0;i<children.getChildren().size();i++) {
            newsItemPages.add(new NewsItemPager(context,children.getChildren().get(i)));
        }

        MyNewsCenterPagerAdapter adapter   = new MyNewsCenterPagerAdapter();
        vp_sub_pager_news.setAdapter(adapter);
        indicator.setViewPager(vp_sub_pager_news);
    }

    class MyNewsCenterPagerAdapter extends PagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return children.getChildren().get(position).title;
        }

        @Override
        public int getCount() {
            return newsItemPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            NewsItemPager newsItemPager = newsItemPages.get(position);
            View currentView = newsItemPager.initView();
            newsItemPager.initData();
            container.addView(currentView);
            return currentView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
