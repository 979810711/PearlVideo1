package yjh.com.cn.pearlvideo.Frgment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.MyAdapter.CommentAdatper;
import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;
import yjh.com.cn.pearlvideo.activity.FrgmentBaseActivity;


/**
 * 首页
 * A simple {@link Fragment} subclass.
 */
public class MainFrgment extends Fragment implements View.OnClickListener {

    private ImageView chats, love, fx_img, report, head, pyq, qq, weix, weib, close;
    private TextView number_size, username, userexplain, cancel, textnumber;
    private RelativeLayout rootlayout, activity_focus_play, sharelayout, commLayout, focus_plays;
    private View view;
    private FrgmentBaseActivity frgmentBaseActivity;
    private Fragment currentFragment;
    private ImageButton focusl;
    private RecyclerView commentRecycler;
    private List<String> list;
    private CommentAdatper comment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        frgmentBaseActivity = (FrgmentBaseActivity) getActivity();
        frgmentBaseActivity.setFullScreen(true);//隐藏状态栏

        view = inflater.inflate(R.layout.fragment_main_frgment, container, false);
        InitView();
        inItData();
        Glide.with(this).load(R.drawable.mei)//暂时默认图片
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(getActivity(), 1, Color.parseColor("#dddddd"))))
                .into(head);

        return view;
    }

    private void inItData() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("璀璨星空" + i);
        }
        commentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        comment = new CommentAdatper(list, getActivity());
        commentRecycler.setAdapter(comment);
        recyclerBottom(commentRecycler);

    }


    //检查是否到了底部
    private void recyclerBottom(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.canScrollVertically(1)) {

                } else {
                    System.out.println("底部");
                }
                if (recyclerView.canScrollVertically(-1)) {

                } else {
                    System.out.println("顶部");
                }
            }
        });

    }


    private void InitView() {

        currentFragment = new Fragment();
        chats = view.findViewById(R.id.chats);
        love = view.findViewById(R.id.love);
        fx_img = view.findViewById(R.id.fx_img);
        report = view.findViewById(R.id.report);
        head = view.findViewById(R.id.head);
        number_size = view.findViewById(R.id.number_size);
        username = view.findViewById(R.id.name);
        userexplain = view.findViewById(R.id.explain);
        activity_focus_play = view.findViewById(R.id.activity_focus_play);
        focusl = view.findViewById(R.id.focus);
        rootlayout = view.findViewById(R.id.hello);
        sharelayout = view.findViewById(R.id.sharelayout);
        focus_plays = view.findViewById(R.id.focus_plays);
        pyq = view.findViewById(R.id.pyq);
        qq = view.findViewById(R.id.qq);
        weix = view.findViewById(R.id.wx);
        weib = view.findViewById(R.id.wb);
        cancel = view.findViewById(R.id.cancel);
        close = view.findViewById(R.id.close);
        textnumber = view.findViewById(R.id.textnumber);
        commLayout = view.findViewById(R.id.commLayout);
        commentRecycler = view.findViewById(R.id.commentRecycler);

        cancel.bringToFront();
        chats.setOnClickListener(this);
        love.setOnClickListener(this);
        fx_img.setOnClickListener(this);
        report.setOnClickListener(this);
        close.setOnClickListener(this);

        pyq.setOnClickListener(this);
        commLayout.setOnClickListener(this);
        qq.setOnClickListener(this);
        weix.setOnClickListener(this);
        weib.setOnClickListener(this);
        cancel.setOnClickListener(this);
        head.setOnClickListener(this);
        focusl.setOnClickListener(this);
        close.setOnClickListener(this);
        focus_plays.setOnClickListener(this);

    }

    //切换fragment
    private void switchFragment(Fragment targetFragment) {
        Bundle bundle = new Bundle();
        bundle.putString("key", "MyFocusPlayActivity");
        bundle.putString("status", "hidden");
        targetFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out);
        if (!targetFragment.isAdded()) {
            transaction
                    .hide(currentFragment)
                    .replace(R.id.fragment_layout, targetFragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fx_img: //分享按钮
                if (sharelayout.getVisibility() == View.GONE)
                    startAppearanceAnimation();
                break;
            case R.id.cancel: //隐藏分享面板
                if (sharelayout.getVisibility() == View.VISIBLE)
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
            case R.id.head://点击头像跳个人中心
                switchFragment(new MyFragment());
                break;
            case R.id.focus://关注按钮
                focusl.setImageResource(R.mipmap.gzcg);
                break;
            case R.id.close: //关闭评论面板
                if (commLayout.getVisibility() == View.VISIBLE)
                    endCommLayout();
                break;
            case R.id.focus_plays: //关闭评论面板和分享面板
                if (commLayout.getVisibility() == View.VISIBLE) {
                    endCommLayout();
                } else if (sharelayout.getVisibility() == View.VISIBLE) {
                    endDisappearanceAnimation();
                }
                break;
            case R.id.chats: //打开评论面板
                if (commLayout.getVisibility() == View.GONE)
                    startCommLayout();
                break;
        }

    }


    private void startCommLayout() {
        commLayout.setVisibility(View.VISIBLE);
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 190.0f, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        commLayout.startAnimation(animationSet);
    }

    private void endCommLayout() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 300.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        commLayout.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                commLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }


    /**
     * 启动动画
     */
    private void startAppearanceAnimation() {
        sharelayout.bringToFront();
        rootlayout.setVisibility(View.VISIBLE);
        sharelayout.setVisibility(View.VISIBLE);
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
        sharelayout.startAnimation(animationSet);
    }

    /**
     * 结束动画
     */
    private void endDisappearanceAnimation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 200.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        sharelayout.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                rootlayout.setVisibility(View.GONE);
                sharelayout.setVisibility(View.GONE);
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
        frgmentBaseActivity.QQshare();

    }

    //微信
    public void weiXin() {
        frgmentBaseActivity.weiXin();
    }

    //微博
    public void sina() {
        frgmentBaseActivity.sina();
    }

    //朋友圈
    public void weixinCircle() {
        frgmentBaseActivity.weixinCircle();
    }

}
