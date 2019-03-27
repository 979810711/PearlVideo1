package yjh.com.cn.pearlvideo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.MyAdapter.MyAllRecAdapter;
import yjh.com.cn.pearlvideo.MyAdapter.MyTabPagerAdapter;
import yjh.com.cn.pearlvideo.MyAdapter.SpacesItemDecoration;
import yjh.com.cn.pearlvideo.PullToRefresh.PullToRefreshLayout;
import yjh.com.cn.pearlvideo.R;

public class MessageActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager messageViewPager;
    private TabLayout tabLayout;
    private ImageView back;
    private List<String> listTitles = new ArrayList<>();//页卡标题集合
    private MyTabPagerAdapter myAdapter;
    private List<View> viewList;
    private LayoutInflater inflater = null;
    private View view, view2, view3, view4, view5, view6;
    private RecyclerView allRecyclerView;
    private MyAllRecAdapter myAllRecAdapter;
    private List<String> nameList;
    private PullToRefreshLayout pullToRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        InitView();
    }

    private void InitView() {
        messageViewPager = (ViewPager) findViewById(R.id.messageViewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        back = (ImageView) findViewById(R.id.back);
        initData();

    }


    /**
     * 设置适配器
     */
    private void setMyAllRecAdapter() {
        nameList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            nameList.add("我的名字" + i);
        }
        allRecyclerView.addItemDecoration(new SpacesItemDecoration(10, 0, 0, 14));//设置item间隔
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        allRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        myAllRecAdapter = new MyAllRecAdapter(nameList, this);
        allRecyclerView.setAdapter(myAllRecAdapter);
    }

    private void initData() {
        viewList = new ArrayList<>();
        listTitles.add("全部消息");
        listTitles.add("评论");
        listTitles.add("点赞");
        listTitles.add("关注");
        listTitles.add("转发");
        listTitles.add("通知");
        for (int i = 0; i < listTitles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(listTitles.get(i)));//添加tab选项
        }
        inflater = LayoutInflater.from(MessageActivity.this);
        view = inflater.inflate(R.layout.messagelayout, null);
        view2 = inflater.inflate(R.layout.messagelayout, null);
        view3 = inflater.inflate(R.layout.messagelayout, null);
        view4 = inflater.inflate(R.layout.messagelayout, null);
        view5 = inflater.inflate(R.layout.messagelayout, null);
        view6 = inflater.inflate(R.layout.messagelayout, null);
        allRecyclerView = view.findViewById(R.id.allRecyclerview);
        pullToRefreshLayout = view.findViewById(R.id.f1);
        pullToRefreshLayout.setOnRefreshListener(new MyListenerss());
        allRecyclerView.setNestedScrollingEnabled(false);
        viewList.add(view);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);
        setMyAllRecAdapter();

        myAdapter = new MyTabPagerAdapter(viewList, listTitles);
        messageViewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(messageViewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(myAdapter);//给Tabs设置适配器
        for (int i = 0; i < tabLayout.getTabCount(); i++) {//获得每一个tab
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
            if(i==0){//设置tltle上面的提示
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
            }else if(i==3){
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.VISIBLE);
            }
        }
        updateTabTextView(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()), true);
        tabLayout.addOnTabSelectedListener(this);
        myAllRecAdapter.setOnItemClickListener(new MyAllRecAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MessageActivity.this, "点击的是" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private View getTabView(int currentPosition) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tab_item_textview);
        textView.setText(listTitles.get(currentPosition));

        return view;
    }


    /**
     * 设置选中后的字体大小
     *
     * @param tab
     * @param isSelect
     */
    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
        if (isSelect) {
            //选中加粗
            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabSelect.setTextSize(20);
            tabSelect.setTextColor(Color.parseColor("#ffe11379"));
            tabSelect.setText(tab.getText());
        } else {
            TextView tabUnSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            tabUnSelect.setTextColor(Color.parseColor("#ff333333"));
            tabUnSelect.setTextSize(16);
            tabUnSelect.setText(tab.getText());
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        updateTabTextView(tab, true);
        switch (tab.getPosition()) {
            case 0:
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
                break;
            case 1:
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
            break;
            case 2:
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
                break;
            case 3:
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
                break;
            case 4:
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
                break;
            case 5:
                tab.getCustomView().findViewById(R.id.backName).setVisibility(View.GONE);
                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        updateTabTextView(tab, false);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public class MyListenerss implements PullToRefreshLayout.OnRefreshListener {


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件刷新完毕了哦！
                    Toast.makeText(MessageActivity.this, "上拉", Toast.LENGTH_SHORT).show();
                    pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                    //    list.clear();

                }
            }.sendEmptyMessageDelayed(0, 2000);
        }

        @Override
        public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
            // 加载操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件加载完毕了哦！
                    Toast.makeText(MessageActivity.this, "下拉->", Toast.LENGTH_SHORT).show();
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    //      list.clear();


                }
            }.sendEmptyMessageDelayed(0, 2000);
        }

    }
}
