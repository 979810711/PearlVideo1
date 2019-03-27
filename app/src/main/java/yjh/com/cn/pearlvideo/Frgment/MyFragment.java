package yjh.com.cn.pearlvideo.Frgment;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.MyAdapter.MyAdapter;
import yjh.com.cn.pearlvideo.MyAdapter.MyRescAdapter;
import yjh.com.cn.pearlvideo.MyAdapter.SpacesItemDecoration;
import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.AgeUtils;
import yjh.com.cn.pearlvideo.Utlis.CustomViewPager;
import yjh.com.cn.pearlvideo.Utlis.DepthPageTransformer;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;
import yjh.com.cn.pearlvideo.Utlis.ViewPagerScroller;
import yjh.com.cn.pearlvideo.activity.EditorActivity;
import yjh.com.cn.pearlvideo.activity.FocusPlayActivity;
import yjh.com.cn.pearlvideo.activity.FrgmentBaseActivity;
import yjh.com.cn.pearlvideo.activity.MessageActivity;
import yjh.com.cn.pearlvideo.activity.MyMakeActivity;
import yjh.com.cn.pearlvideo.activity.SettingActivity;
import yjh.com.cn.pearlvideo.activity.TeamActivity;

import static yjh.com.cn.pearlvideo.R.id.team;

/**
 * 我的
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener, FrgmentBaseActivity.onMyKeyDown {


    private View view, view1, view2;
    private ImageView tx_img, setting, zf_img, pyq, qq, weix, weib, lt_img, editor, sex, call,nowork;
    private CustomViewPager viewPager;
    private LayoutInflater inflater = null;
    private MyAdapter adapter = null;
    private TextView zp_text, t1, xh_text, t2, cancel, number_1, number_2, team_txte, name, age, letter,hinttext;
    private RecyclerView worksRecy, likeRecy, works_test;
    private MyRescAdapter mywksAdapter, likeAdapter;
    private List<Integer> mylist, mylist1;
    private NestedScrollView nestedScrollView;
    private List<View> list;
    private RelativeLayout rootlayout, isRelative, compileLayout;
    private Uri url = null;
    private FrgmentBaseActivity f;
    private FocusPlayActivity f1;
    private String status="";//传过来的值


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        inItView();
        judgeActivity();
        getActivityKey();
        setViewPagerAdapter();
        Glide.with(this).load(R.drawable.mei)//暂时默认图片
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(getActivity(), 3, Color.parseColor("#dddddd"))))
                .into(tx_img);
        return view;
    }

    //判断是从那个activity跳过来
    private boolean judgeActivity() {
        try {
            if (getActivity() instanceof FocusPlayActivity) {
                f1 = (FocusPlayActivity) getActivity();
                return false;
            } else {
                f = (FrgmentBaseActivity) getActivity();
                if (f != null) {
                    f.setOnKeyDown(this);
                }
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;

    }

    //查看activity传过来的值
    private void getActivityKey() {
        if (null != getArguments()) {
            String text = getArguments().getString("key");
            status = getArguments().getString("status");
            if (text.equalsIgnoreCase("MyFocusPlayActivity")) { //从关注的activity过来
                setting.setVisibility(View.GONE);
                lt_img.setVisibility(View.GONE);
                call.setBackgroundResource(R.mipmap.gzan);
                team_txte.setVisibility(View.GONE);
                editor.setVisibility(View.GONE);
                compileLayout.setVisibility(View.GONE);
            }
            if (status.equals("hidden")) { //从首页头像过来
                f.setCodingLayoutGone();
            }
        }


    }

    /**
     * 初始化控件
     */
    private void inItView() {
        setting = view.findViewById(R.id.setting);
        tx_img = view.findViewById(R.id.tx_img);
        zf_img = view.findViewById(R.id.zf_img);
        editor = view.findViewById(R.id.editor);
        viewPager = view.findViewById(R.id.mypager);
        zp_text = view.findViewById(R.id.zp_text);
        number_1 = view.findViewById(R.id.number_1);
        number_2 = view.findViewById(R.id.number_2);
        team_txte = view.findViewById(team);
        age = view.findViewById(R.id.age);
        name = view.findViewById(R.id.name);
        nowork = view.findViewById(R.id.nowork);
        hinttext=view.findViewById(R.id.hinttext);
        sex = view.findViewById(R.id.sex);
        letter = view.findViewById(R.id.letter);
        call = view.findViewById(R.id.call);
        compileLayout = view.findViewById(R.id.compileLayout);

        t1 = view.findViewById(R.id.t1);
        xh_text = view.findViewById(R.id.xh_text);
        t2 = view.findViewById(R.id.t2);
        lt_img = view.findViewById(R.id.lt_img);
        nestedScrollView = view.findViewById(R.id.nestedView);
        rootlayout = view.findViewById(R.id.hello);
        isRelative = view.findViewById(R.id.isRelative);

        pyq = view.findViewById(R.id.pyq);
        qq = view.findViewById(R.id.qq);
        weix = view.findViewById(R.id.wx);
        weib = view.findViewById(R.id.wb);
        cancel = view.findViewById(R.id.cancel);
        cancel.bringToFront();

        setting.setOnClickListener(this);
        team_txte.setOnClickListener(this);
        zp_text.setOnClickListener(this);
        xh_text.setOnClickListener(this);
        tx_img.setOnClickListener(this);
        zf_img.setOnClickListener(this);
        lt_img.setOnClickListener(this);
        cancel.setOnClickListener(this);
        isRelative.setOnClickListener(this);
        pyq.setOnClickListener(this);
        weix.setOnClickListener(this);
        weib.setOnClickListener(this);
        qq.setOnClickListener(this);
        number_1.setOnClickListener(this);
        number_2.setOnClickListener(this);
        editor.setOnClickListener(this);


    }

    /**
     * 设置适配器
     */
    private void setViewPagerAdapter() {
        list = new ArrayList<>();
        inflater = LayoutInflater.from(getActivity());
        view1 = inflater.inflate(R.layout.viewpagerlayout, null);
        view2 = inflater.inflate(R.layout.viewpagerlayout2, null);
        worksRecy = view1.findViewById(R.id.works_1);
        works_test = view1.findViewById(R.id.works_test);
        likeRecy = view2.findViewById(R.id.works_2);
        list.add(view1);
        list.add(view2);
        adapter = new MyAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        setAdapter();
        nestedScrollView.setFillViewport(true);
        likeRecy.setNestedScrollingEnabled(false);
        worksRecy.setNestedScrollingEnabled(false);
        works_test.setNestedScrollingEnabled(false);

        //设置滑动速度
        ViewPagerScroller scroller = new ViewPagerScroller(getActivity());
        scroller.setScrollDuration(800);
        scroller.initViewPagerScroll(viewPager);//这个是设置切换过渡时间为2秒
        viewPager.setPageTransformer(true, new DepthPageTransformer());


    }

    private void setAdapter() {
        mylist = new ArrayList<>();
        mylist1 = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            mylist.add(R.drawable.bm);
        }
        for (int i = 0; i < 30; i++) {
            mylist1.add(R.drawable.bm);
        }
        if(mylist.size()>0){
            nowork.setVisibility(View.GONE);
            hinttext.setVisibility(View.GONE);
        }
        works_test.addItemDecoration(new SpacesItemDecoration(0, 3, 0, 4));//设置item间隔
        works_test.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        mywksAdapter = new MyRescAdapter(mylist);
        works_test.setAdapter(mywksAdapter);

        worksRecy.addItemDecoration(new SpacesItemDecoration(0, 3, 0, 4));//设置item间隔
        worksRecy.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        mywksAdapter = new MyRescAdapter(mylist);

        likeRecy.addItemDecoration(new SpacesItemDecoration(0, 3, 0, 4));//设置item间隔
        likeRecy.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));
        mywksAdapter = new MyRescAdapter(mylist1);
        likeRecy.setAdapter(mywksAdapter);


    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


        viewPager.requestLayout();
        if (position == 0) {
            zp_text.setTextColor(Color.parseColor("#ff333333"));
            xh_text.setTextColor(Color.parseColor("#666666"));
            t1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.GONE);

        } else if (position == 1) {
            zp_text.setTextColor(Color.parseColor("#666666"));
            xh_text.setTextColor(Color.parseColor("#ff333333"));
            t2.setVisibility(View.VISIBLE);
            t1.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zp_text://viewpagr 作品
                viewPager.setCurrentItem(0);
                break;
            case R.id.xh_text://viewpagr 喜欢
                viewPager.setCurrentItem(1);
                break;
            case R.id.setting: //设置
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.zf_img: //分享按钮
                startAppearanceAnimation();
                break;
            case R.id.cancel: //隐藏分享面板
                endDisappearanceAnimation();
                break;
            case R.id.isRelative: ////隐藏分享面板
                if (rootlayout.getVisibility() == View.VISIBLE)
                    endDisappearanceAnimation();
                break;
            case R.id.pyq://朋友圈
                weixinCircle();
                break;
            case R.id.wx://微信
                weiXin();
                break;
            case R.id.qq: //QQ
                qq();
                break;
            case R.id.wb://新浪微博
                sina();
                break;
            case R.id.lt_img:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.number_1://关注页面
                Intent intent = new Intent(getActivity(), MyMakeActivity.class);
                intent.putExtra("key", "关注");
                startActivity(intent);
                break;
            case R.id.number_2://粉丝页面
                Intent intent1 = new Intent(getActivity(), MyMakeActivity.class);
                intent1.putExtra("key", "我的粉丝");
                startActivity(intent1);
                break;
            case team://团队页面
                startActivity(new Intent(getActivity(), TeamActivity.class));
                break;
            case R.id.editor://编辑给人资料
                startActivityForResult(new Intent(getActivity(), EditorActivity.class), 1);
                break;


        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 3) {
            String UserSex = data.getStringExtra("UserSex");
            String UserName = data.getStringExtra("UserName");
            String UserDay = data.getStringExtra("UserDay");
            String edit_geyan = data.getStringExtra("edit_geyan");
            if (!data.getStringExtra("url").equalsIgnoreCase("")) {
                url = Uri.parse(data.getStringExtra("url"));
                if (url != null) {
                    Glide.with(this).load(url)
                            .apply(new RequestOptions()
                                    .skipMemoryCache(true) // 不使用内存缓存
                                    .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                                    .placeholder(R.mipmap.ic_launcher).centerCrop()
                                    .transform(new GlideCircleTransform(getActivity(), 3, Color.parseColor("#dddddd"))))
                            .into(tx_img);
                }
            }
            setUserDate(UserName, edit_geyan, UserDay, UserSex);

        }
    }

    //用户资料
    private void setUserDate(String UserName, String geyan, String userage, String usersex) {
        if (!UserName.equalsIgnoreCase("")) {
            name.setText(UserName);
        }
        if (!geyan.equalsIgnoreCase("")) {
            letter.setText(geyan);
        }
        if (!userage.equalsIgnoreCase("")) {
            age.setText(AgeUtils.getAgeFromBirthTime(userage) + "");
        }
        if (!usersex.equalsIgnoreCase("")) {
            if (usersex.equalsIgnoreCase("男")) {
                sex.setBackgroundResource(R.mipmap.nan);
            } else {
                sex.setBackgroundResource(R.mipmap.nv);
            }
        }
    }

    /**
     * 启动动画
     */
    private void startAppearanceAnimation() {

        if (rootlayout.getVisibility() == View.VISIBLE) {
            return;
        }
        rootlayout.setVisibility(View.VISIBLE);
        pyq.setVisibility(View.VISIBLE);
        qq.setVisibility(View.VISIBLE);
        weix.setVisibility(View.VISIBLE);
        weib.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 100.0f, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        rootlayout.startAnimation(animationSet);

    }

    /**
     * 借宿动画
     */
    private void endDisappearanceAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 200.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        rootlayout.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rootlayout.setVisibility(View.GONE);
                pyq.setVisibility(View.GONE);
                qq.setVisibility(View.GONE);
                weix.setVisibility(View.GONE);
                weib.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    //QQ分享
    public void qq() {
        if (judgeActivity()) {
            f.QQshare();
        } else {
            f1.QQshare();
        }

    }

    //微信
    public void weiXin() {
        if (judgeActivity()) {
            f.weiXin();
        } else {
            f1.weiXin();
        }
    }

    //微博
    public void sina() {
        if (judgeActivity()) {
            f.sina();
        } else {
            f1.sina();
        }

    }

    //朋友圈
    public void weixinCircle() {
        if (judgeActivity()) {
            f.weixinCircle();
        } else {
            f1.weixinCircle();
        }
    }


    //退出动画
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {

        if(enter){
            return super.onCreateAnimation(transit, enter, nextAnim);
        }else {
            return AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_left_out);
        }

    }

    @Override
    public boolean onTouchEvent(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK && status.equalsIgnoreCase("hidden") && judgeActivity()) {
            if(f!=null) {
                f.setCodingLayoutVisible();
            }
        }
        return true;
    }


}
