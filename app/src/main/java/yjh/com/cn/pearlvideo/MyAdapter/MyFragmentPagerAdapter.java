package yjh.com.cn.pearlvideo.MyAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 979810711 on 2019/3/18.
 */

public class MyFragmentPagerAdapter  extends FragmentPagerAdapter {
    private FragmentManager fragmetnmanager;  //创建FragmentManager

    private List<Fragment> listfragment; //创建一个List<Fragment>

    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.fragmetnmanager=fm;
        this.listfragment=list;
    }
    @Override

    public Fragment getItem(int arg0) {

        return listfragment.get(arg0); //返回第几个fragment

    }



    @Override

    public int getCount() {
        return listfragment.size(); //总共有多少个fragment

    }




}
