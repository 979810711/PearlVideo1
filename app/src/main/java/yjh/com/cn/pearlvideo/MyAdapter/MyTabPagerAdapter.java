package yjh.com.cn.pearlvideo.MyAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 979810711 on 2019/3/21.
 */

public class MyTabPagerAdapter extends PagerAdapter {

    List<View> list;
    List<String> stringList;


    public MyTabPagerAdapter(List<View> list, List<String> stringList) {
        this.list = list;
        this.stringList = stringList;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        ((ViewPager) container).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        ((ViewPager) container).addView(list.get(position));
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }


}
