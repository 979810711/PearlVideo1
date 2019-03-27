package yjh.com.cn.pearlvideo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.MyAdapter.MyTeamAdaper;
import yjh.com.cn.pearlvideo.MyAdapter.SpacesItemDecoration;
import yjh.com.cn.pearlvideo.R;

public class TeamActivity extends BaseActivity implements View.OnClickListener {

    private ImageView allteam, renz, wrz, back_team;
    private RecyclerView teamList;
    private MyTeamAdaper myTeamAdaper;
    private List<String> list, authList, tolist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        InitView();
    }

    private void InitView() {

        allteam = (ImageView) findViewById(R.id.allteam);
        renz = (ImageView) findViewById(R.id.renz);
        wrz = (ImageView) findViewById(R.id.wrz);
        back_team = (ImageView) findViewById(R.id.back_team);
        teamList = (RecyclerView) findViewById(R.id.teamList);
        allteam.setOnClickListener(this);
        renz.setOnClickListener(this);
        wrz.setOnClickListener(this);
        back_team.setOnClickListener(this);
        inItDate();

    }

    private void inItDate() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("全部" + i);
        }

        authList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            authList.add("已经认证" + i);
        }

        tolist = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            tolist.add("未认证" + i);
        }


        myTeamAdaper = new MyTeamAdaper(list, this);
        teamList.addItemDecoration(new SpacesItemDecoration(0, 0, 0, 5));//设置item间隔
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        teamList.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        teamList.setAdapter(myTeamAdaper);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.allteam:
                myTeamAdaper = new MyTeamAdaper(list, this);
                teamList.setAdapter(myTeamAdaper);
                myTeamAdaper.notifyDataSetChanged();
                allteam.setImageResource(R.mipmap.tmqbj);
                renz.setImageResource(R.mipmap.rzs);
                wrz.setImageResource(R.mipmap.tmrz);

                break;
            case R.id.renz:
                myTeamAdaper = new MyTeamAdaper(authList, this);
                teamList.setAdapter(myTeamAdaper);
                myTeamAdaper.notifyDataSetChanged();

                allteam.setImageResource(R.mipmap.tmqb);
                renz.setImageResource(R.mipmap.rzj);
                wrz.setImageResource(R.mipmap.tmrz);
                break;
            case R.id.wrz:
                myTeamAdaper = new MyTeamAdaper(tolist, this);
                teamList.setAdapter(myTeamAdaper);
                myTeamAdaper.notifyDataSetChanged();
                allteam.setImageResource(R.mipmap.tmqb);
                renz.setImageResource(R.mipmap.rzs);
                wrz.setImageResource(R.mipmap.tmrzj);
                break;
            case R.id.back_team:
                finish();
                break;

        }

    }
}
