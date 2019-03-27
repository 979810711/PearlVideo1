package yjh.com.cn.pearlvideo.Utlis;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 979810711 on 2019/3/18.
 *
 * 禁止左右滑动
 */

public class ViewPagerSlide extends ViewPager {
    private boolean isCanScroll = true;

    public ViewPagerSlide(Context context) {
        super(context);
    }

    public ViewPagerSlide(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public void setScanScroll(boolean isCanScroll){

        this.isCanScroll = isCanScroll;

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return  false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }



}
