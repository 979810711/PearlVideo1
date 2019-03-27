package yjh.com.cn.pearlvideo.Utlis;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * 设置状态栏
 * Created by 979810711 on 2019/3/18.
 */

public class StatusBarUtil {
    public static void setWindowStatusBarColor(Activity activity, String colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.parseColor(colorResId));
            }
        } catch (Exception e) {

            e.printStackTrace();

        }


    }
}
