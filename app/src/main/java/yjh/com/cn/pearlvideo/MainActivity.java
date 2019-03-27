package yjh.com.cn.pearlvideo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.util.HashMap;

import yjh.com.cn.pearlvideo.Utlis.HttpClientUtil;
import yjh.com.cn.pearlvideo.Utlis.UserBean;
import yjh.com.cn.pearlvideo.activity.BaseActivity;
import yjh.com.cn.pearlvideo.activity.FrgmentBaseActivity;
import yjh.com.cn.pearlvideo.okHttpUtlis.JosnCreate;
import yjh.com.cn.pearlvideo.okHttpUtlis.Network;


public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();

    }




    //设置一个默认的id 需要READ_PHONE_STATE权限
    private void getUserid(){
        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        UserBean.getInstance().setUserid(szImei);

    }

    //发送默认的用户ID后台
    private void httpPostUsreid(){
        HashMap<Object,Object> hashMap =new HashMap<>();
        hashMap.put("","");

        new Network(this, HttpClientUtil.httpUrl).performPost(JosnCreate.addJosns("",hashMap), new Network.NetCallBack() {
            @Override
            public void onNetFailure() {

            }

            @Override
            public void onSuccess(JSONObject result) {

            }

            @Override
            public void onError(String result) {

            }
        });



    }

    @SuppressLint("CheckResult")
    private void getPermission() {
        try {
            String[] permissions = new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(permissions).subscribe(granted -> {
                if (granted) {//授予权限直接进入主页
                    getUserid();
                } else {
                    Toast.makeText(this, "权限未开启，部分功能不能使用", Toast.LENGTH_SHORT).show();

                    //  PermissionUtils.userAuth(MainActivity.this);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, FrgmentBaseActivity.class));
                        finish();
                    }
                }, 1500);
            });
        } catch (Exception e) {
            Log.d("Exception", e.getMessage());
        }
    }
}
