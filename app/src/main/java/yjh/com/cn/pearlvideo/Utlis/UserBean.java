package yjh.com.cn.pearlvideo.Utlis;

/**
 * Created by 979810711 on 2019/3/19.
 */

public class UserBean {
    public String mToken="";
    private static UserBean spUtil;
    public String userid="";



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public static UserBean getInstance() {
        if(spUtil == null) {
            Class var0 = UserBean.class;
            synchronized(UserBean.class) {
                if(spUtil == null) {
                    spUtil = new UserBean();
                }
            }
        }

        return spUtil;
    }


    public String getmToken() {
        return mToken;
    }

    public  void setmToken(String mToken) {
        this.mToken = mToken;
    }
}
