package yjh.com.cn.pearlvideo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import yjh.com.cn.pearlvideo.Frgment.MyFragment;
import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;

public class FocusPlayActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back, love, fx_img, report, head, pyq, qq, weix, weib;
    private TextView number_size, username, userexplain, cancel;
    private View view;
    private RelativeLayout rootlayout,activity_focus_play,sharelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen(true);
        setContentView(R.layout.activity_focus_play);
        InitView();
        Glide.with(this).load(R.drawable.mei)//暂时默认图片
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                        .transform(new GlideCircleTransform(this, 3, Color.parseColor("#dddddd"))))
                .into(head);
    }



    private void InitView() {
        back = (ImageView) findViewById(R.id.back);
        love = (ImageView) findViewById(R.id.love);
        fx_img = (ImageView) findViewById(R.id.fx_img);
        report = (ImageView) findViewById(R.id.report);
        head = (ImageView) findViewById(R.id.head);
        number_size = (TextView) findViewById(R.id.number_size);
        username = (TextView) findViewById(R.id.name);
        userexplain = (TextView) findViewById(R.id.explain);
        activity_focus_play = (RelativeLayout) findViewById(R.id.activity_focus_play);

        rootlayout = (RelativeLayout) findViewById(R.id.hello);
        sharelayout = (RelativeLayout) findViewById(R.id.sharelayout);
        pyq = (ImageView) findViewById(R.id.pyq);
        qq = (ImageView) findViewById(R.id.qq);
        weix = (ImageView) findViewById(R.id.wx);
        weib = (ImageView) findViewById(R.id.wb);
        cancel = (TextView) findViewById(R.id.cancel);
        cancel.bringToFront();

        back.setOnClickListener(this);
        love.setOnClickListener(this);
        fx_img.setOnClickListener(this);
        report.setOnClickListener(this);
        activity_focus_play.setOnClickListener(this);

        pyq.setOnClickListener(this);
        qq.setOnClickListener(this);
        weix.setOnClickListener(this);
        weib.setOnClickListener(this);
        cancel.setOnClickListener(this);
        head.setOnClickListener(this);






    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head://个人中心
                switchFragment(new MyFragment());
                break;
            case R.id.fx_img: //分享按钮
                if (sharelayout.getVisibility() == View.GONE)
                startAppearanceAnimation();
                break;
            case R.id.cancel: //隐藏分享面板
                if (sharelayout.getVisibility() == View.VISIBLE)
                endDisappearanceAnimation();
                break;
            case R.id.activity_focus_play: ////隐藏分享面板
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
                QQshare();
                break;
            case R.id.wb://新浪微博
                sina();
                break;
            case R.id.back:
                finish();
                break;



        }


    }
//显示个人中心
    private FragmentTransaction switchFragment(Fragment targetFragment) {
        Bundle bundle = new Bundle();
        bundle.putString("key", "MyFocusPlayActivity");
        bundle.putString("status", "");
        targetFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_right_in,R.anim.fragment_left_out);
        transaction.add(R.id.fragment1_layout, targetFragment, targetFragment.getClass().getName());
        transaction.addToBackStack(null);
        //提交事物
        transaction.commit();
        return transaction;
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


}
