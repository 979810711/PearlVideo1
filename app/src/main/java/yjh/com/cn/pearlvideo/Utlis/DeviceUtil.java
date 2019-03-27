package yjh.com.cn.pearlvideo.Utlis;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by 979810711 on 2019/3/18.
 */

public class DeviceUtil {

    /**
     * 设置字体大小
     *
     * @param context
     * @param dimens
     * @return
     */
    public static int setSpSize(Context context, int dimens) {
        float pxValue = context.getResources().getDimension(dimens);//获取对应资源文件下的sp值
        int spValue = ConvertUtils.px2sp(context, pxValue);//将px值转换成sp值
        return spValue;
    }
    /**
     * 设置普通view大小
     *
     * @param context
     * @param dimens
     * @return
     */
    public static int setDpSize(Context context, int dimens) {
             /*获取dp值*/
        float pxValue2 = context.getResources().getDimension(dimens);//获取对应资源文件下的dp值
        int dpValue = ConvertUtils.px2dp(context, pxValue2);//将px值转换成dp值
        return dpValue;
    }

    public static void setPositionAndSize(View view, int width, int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

}
