package com.itcast.www.myapplication.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itcast.www.myapplication.MainActivity;
import com.itcast.www.myapplication.R;
import com.itcast.www.myapplication.bean.NewsCenterBean;
import com.itcast.www.myapplication.pager.NewsCenterPage;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * Created by LSL on 2016/5/22.
 */
public class MenuFragment extends BaseFragment {

    private List<NewsCenterBean.DataBean> data;

    private int currentPosition;

    @ViewInject(R.id.lv_menu_news_center)
    private ListView lv_menu_news_center;
    private MyBaseAdapter adapter;

    @Override
    public View initView() {

        View view = View.inflate(context, R.layout.fragment_menu, null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public Void initData() {
        return null;
    }

    public void initSubMenu(List<NewsCenterBean.DataBean> data) {
        this.data = data;
        adapter = new MyBaseAdapter();
        lv_menu_news_center.setAdapter(adapter);
        lv_menu_news_center.setOnItemClickListener(new MyOnItemClickListener());
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder =null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.listview_item_menu, null);
                viewHolder.iv_menu_item = (ImageView) convertView.findViewById(R.id.iv_menu_item);
                viewHolder.tv_menu_item = (TextView) convertView.findViewById(R.id.tv_menu_item);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            NewsCenterBean.DataBean dataBean = data.get(i);
            viewHolder.tv_menu_item.setText(dataBean.title);
            if(currentPosition ==i){
                viewHolder.iv_menu_item.setBackgroundResource(R.drawable.menu_arr_select);
                viewHolder.tv_menu_item.setTextColor(Color.RED);
                convertView.setBackgroundResource(R.drawable.menu_item_bg_select);
            }else{
                viewHolder.iv_menu_item.setBackgroundResource(R.drawable.menu_arr_normal);
                viewHolder.tv_menu_item.setTextColor(Color.WHITE);
                convertView.setBackground(null);
            }
            return convertView;
        }

        public  class ViewHolder {
            public ImageView iv_menu_item;
            public TextView tv_menu_item;
        }
    }

    private class MyOnItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //刷新被选中条目的样式
            currentPosition = i;
            adapter.notifyDataSetChanged();
            //关闭二级菜单
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.toggle();

            //切换新闻中心区域的显示内容
            ContentFragment contentFragment = mainActivity.getContentFragmentByTag();
            NewsCenterPage newsCenterPage = (NewsCenterPage) contentFragment.getPages().get(1);

            newsCenterPage.switchSubMenu(i);

        }
    }
}
