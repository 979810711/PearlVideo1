package yjh.com.cn.pearlvideo.Frgment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.MyAdapter.FocusAdapter;
import yjh.com.cn.pearlvideo.MyAdapter.SpacesItemDecoration;
import yjh.com.cn.pearlvideo.PullToRefresh.PullToRefreshLayout;
import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.activity.FocusPlayActivity;

/**
 * 关注
 * A simple {@link Fragment} subclass.
 */
public class CareFragment extends Fragment implements View.OnClickListener {


    private View view;
    private FocusAdapter focusAdapter;
    private PullToRefreshLayout pullToRefreshLayout;
    private RecyclerView focusRecyclerView;
    private List<String> list;
    private CareFragment currentFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_care, container, false);
        inItView();
        inItDate();
        currentFragment =new CareFragment();
        return view;
    }


    private void inItView() {
        pullToRefreshLayout = view.findViewById(R.id.rootRefresh);
        focusRecyclerView = view.findViewById(R.id.focusRecyclerView1);
    }

    private void inItDate() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("我的名字" + i);
        }
        focusRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 0));//设置item间隔
        focusRecyclerView.setNestedScrollingEnabled(false);
        focusAdapter = new FocusAdapter(list, getActivity());
        focusRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        focusRecyclerView.setAdapter(focusAdapter);
        pullToRefreshLayout.setOnRefreshListener(new MyListenerss());
        focusAdapter.setOnItemClickListener(new FocusAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, View v) {
               startActivity(new Intent(getActivity(), FocusPlayActivity.class));
            }
        });
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }

    }

    public class MyListenerss implements PullToRefreshLayout.OnRefreshListener {


        @Override
        public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
            // 下拉刷新操作
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    // 千万别忘了告诉控件刷新完毕了哦！
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
                    pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                    //      list.clear();


                }
            }.sendEmptyMessageDelayed(0, 2000);
        }

    }
}
