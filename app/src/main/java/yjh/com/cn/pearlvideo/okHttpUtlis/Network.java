package yjh.com.cn.pearlvideo.okHttpUtlis;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import yjh.com.cn.pearlvideo.Utlis.UserBean;

/**
 * Created by 979810711 on 2019/3/19.
 */

public class Network {
    //上下文
    private Context mContext;
    //请求的url
    private String mUrl;
    //通过单例类得到的OkHttpClient 对象
    private OkHttpClient mClient;
    //解析服务器返回数据的Gson
    private Gson mGson;
    private Handler mHandler;
    //登录之后返回的Token字符串
    private String mToken;
    private static Network network;



    public  Network(Context context, String url) {
        this.mContext = context;
        this.mUrl = url;
        mClient = OkHttpClientInstance.getInstance();
        mGson = new Gson();
        mHandler = new Handler();
        //获取Token的方式可以在进入程序时获取，存为全局变量
        mToken = UserBean.getInstance().getmToken();
    }

    /**
     * 执行普通的post请求，参数集合全部转为json
     *
     * @param
     * @param netCallBack  回调接口
     */
    public void performPost(JSONObject jsonObject, final NetCallBack netCallBack) {
        //String params = mGson.toJson(map);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final Request request = new Request.Builder()
                .url(mUrl)
                .addHeader("Authorization", "Bearer" + mToken)
                .post(body)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                doFailure(netCallBack);
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                doResponse(response, netCallBack);
            }
        });
    }



    /**
     * 处理失败
     *
     * @param netCallBack
     */
    private void doFailure(final NetCallBack netCallBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                netCallBack.onNetFailure();
            }
        });
    }

    /**
     * 处理成功
     *
     * @param response
     * @param netCallBack
     * @throws IOException
     */
    private void doResponse(Response response, final NetCallBack netCallBack) throws IOException {
        final String result = response.body().string();
        Log.d("result===",result);
        if (!TextUtils.isEmpty(result)) {
            try {
                final JSONObject jsonObject = new JSONObject(result);
                String success = jsonObject.getString("code");

                if ("S".equals(success)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            netCallBack.onSuccess(jsonObject);
                        }
                    });
                } else if ("E".equals(success)) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            netCallBack.onError(result);
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //服务器返回错误
                        netCallBack.onError(result);
                    }
                });
            }
        }
    }


    /**
     * 回调接口
     */
    public interface NetCallBack {

        //网络请求失败，没连网
        void onNetFailure();

        //网络请求成功
        void onSuccess(JSONObject result);


        void onError(String result);
    }

}
