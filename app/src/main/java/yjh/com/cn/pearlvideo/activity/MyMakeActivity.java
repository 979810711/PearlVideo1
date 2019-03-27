package yjh.com.cn.pearlvideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.MyAdapter.MyMakeAdapter;
import yjh.com.cn.pearlvideo.MyAdapter.SpacesItemDecoration;
import yjh.com.cn.pearlvideo.PullToRefresh.PullToRefreshLayout;
import yjh.com.cn.pearlvideo.R;

public class MyMakeActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView allRecyclerView;
    private PullToRefreshLayout pullToRefreshLayout;
    private ImageView back_myMake;
    private MyMakeAdapter myMakeAdapter;
    private List<String> list;
    private TextView title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_make);
        InitView();
        showIntentData();

    }
    private void showIntentData(){
        Intent intent =getIntent();
        if(intent!=null){
            title.setText(intent.getStringExtra("key"));
        }
    }

    private void InitView(){
        allRecyclerView = (RecyclerView) findViewById(R.id.allRecyclerview);
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.f1);
        back_myMake = (ImageView) findViewById(R.id.back_myMake);
        title = (TextView) findViewById(R.id.title);
        allRecyclerView.setNestedScrollingEnabled(false);
        pullToRefreshLayout.setOnRefreshListener(new MyListenerss());
        setAdaper();
    }

    private void setAdaper(){
        list=new ArrayList<>();
        for (int i = 0; i <40 ; i++) {
            list.add("我的名字"+i);
        }
        allRecyclerView.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 5));//设置item间隔
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        allRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        myMakeAdapter =new MyMakeAdapter(list,this);
        allRecyclerView.setAdapter(myMakeAdapter);

        myMakeAdapter.setOnItemClickListener(new MyMakeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MyMakeActivity.this, "点击了="+position, Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public void onClick(View view) {

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
