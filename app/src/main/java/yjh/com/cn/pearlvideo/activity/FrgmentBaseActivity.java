package yjh.com.cn.pearlvideo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import yjh.com.cn.pearlvideo.Frgment.CareFragment;
import yjh.com.cn.pearlvideo.Frgment.MainFrgment;
import yjh.com.cn.pearlvideo.Frgment.MyFragment;
import yjh.com.cn.pearlvideo.Frgment.PearlFrgment;
import yjh.com.cn.pearlvideo.MyAdapter.MyFragmentPagerAdapter;
import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.ViewPagerSlide;


public class FrgmentBaseActivity extends BaseActivity implements View.OnClickListener {

    private ImageView sy_img, gz_img, zz_img, wd_img;
    private ViewPagerSlide viewPager;
    private List<Fragment> listfragment;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private ImageButton photo;
    public RelativeLayout codingLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frgment_base);
        initView();
        listfragment = new ArrayList<>();
        setViewPagerAdapter();
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        //      viewPager.setSlide(false);//禁止滑动

    }

    private void initView() {
        sy_img = (ImageView) findViewById(R.id.sy);
        gz_img = (ImageView) findViewById(R.id.gz);
        zz_img = (ImageView) findViewById(R.id.zz);
        wd_img = (ImageView) findViewById(R.id.wd);
        viewPager = (ViewPagerSlide) findViewById(R.id.viewpager);
        photo = (ImageButton) findViewById(R.id.photo);
        codingLayout = (RelativeLayout) findViewById(R.id.reLayouts);

        sy_img.setOnClickListener(this);
        gz_img.setOnClickListener(this);
        zz_img.setOnClickListener(this);
        wd_img.setOnClickListener(this);
        photo.setOnClickListener(this);
    }

    public void setCodingLayoutGone(){
        codingLayout.setVisibility(View.GONE);
    }

    public void setCodingLayoutVisible(){
        codingLayout.setVisibility(View.VISIBLE);
    }

    private void setViewPagerAdapter() {
        MainFrgment f1 = new MainFrgment();
        CareFragment f2 = new CareFragment();
        PearlFrgment f3 = new PearlFrgment();
        MyFragment f4 = new MyFragment();
        listfragment.add(f1);
        listfragment.add(f2);
        listfragment.add(f3);
        listfragment.add(f4);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), listfragment);
        viewPager.setAdapter(myFragmentPagerAdapter);


    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.sy ) {
            viewPager.setCurrentItem(0);
            sy_img.setImageResource(R.mipmap.xh_02);
            gz_img.setImageResource(R.mipmap.xh1_03);
            zz_img.setImageResource(R.mipmap.xh1_05);
            wd_img.setImageResource(R.mipmap.xh1_06);
        } else if (view.getId() == R.id.gz) {
            viewPager.setCurrentItem(1);
            sy_img.setImageResource(R.mipmap.xh1_02);
            gz_img.setImageResource(R.mipmap.xh_03);
            zz_img.setImageResource(R.mipmap.xh1_05);
            wd_img.setImageResource(R.mipmap.xh1_06);
        } else if (view.getId() == R.id.zz ) {
            viewPager.setCurrentItem(2);
            sy_img.setImageResource(R.mipmap.xh1_02);
            gz_img.setImageResource(R.mipmap.xh1_03);
            zz_img.setImageResource(R.mipmap.xh_05);
            wd_img.setImageResource(R.mipmap.xh1_06);
        } else if (view.getId() == R.id.wd ) {
            viewPager.setCurrentItem(3);
            sy_img.setImageResource(R.mipmap.xh1_02);
            gz_img.setImageResource(R.mipmap.xh1_03);
            zz_img.setImageResource(R.mipmap.xh1_05);
            wd_img.setImageResource(R.mipmap.xh_06);
        }else if(view.getId() == R.id.photo){
            Toast.makeText(this, "暂无", Toast.LENGTH_SHORT).show();
        }

    }





    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(onKeyDown!=null) {
            onKeyDown.onTouchEvent(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);

    }

    public void setOnKeyDown(onMyKeyDown onKeyDown) {
        this.onKeyDown=onKeyDown;
    }

    onMyKeyDown onKeyDown;
    public interface onMyKeyDown {
        /** onTOuchEvent的实现 */
        boolean onTouchEvent(int keyCode, KeyEvent event);
    }


}
