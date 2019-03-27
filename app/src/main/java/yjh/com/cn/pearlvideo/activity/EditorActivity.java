package yjh.com.cn.pearlvideo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import yjh.com.cn.pearlvideo.R;
import yjh.com.cn.pearlvideo.Utlis.GlideCircleTransform;

public class EditorActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back_edit, ok_img, toux, ImageView, sex_nan1, sex_nv,xc_img,xj_img;
    private RelativeLayout sex_layout, day_layout, bj_layout,layout_icon;
    private TextView number_base, cancel, sex_user, day_data,cancel_icon,user_te1,user_te2;
    private EditText edit_geyan, edit_name;
    private static final int PHOTO_REQUEST_CAREMA = 7;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 8;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 9;// 结果
    private File tempFile;
    private String userUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        inItview();
        setUserIcon(toux);

    }


    private void setUserIcon(ImageView img){
        Glide.with(this).load(R.drawable.mei)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher).centerCrop()
                        .transform(new GlideCircleTransform(EditorActivity.this, 3, Color.parseColor("#dddddd"))))
                .into(img);
    }

    private void inItview() {
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_geyan = (EditText) findViewById(R.id.edit_geyan);
        number_base = (TextView) findViewById(R.id.number_base);
        sex_user = (TextView) findViewById(R.id.sex_2);
        user_te1 = (TextView) findViewById(R.id.user_te1);
        user_te2 = (TextView) findViewById(R.id.user_te2);
        sex_layout = (RelativeLayout) findViewById(R.id.sex_layout);
        day_layout = (RelativeLayout) findViewById(R.id.day_layout);
        layout_icon = (RelativeLayout) findViewById(R.id.layout_icon);
        day_data = (TextView) findViewById(R.id.day_data);
        bj_layout = (RelativeLayout) findViewById(R.id.bj_layout);
        back_edit = (ImageView) findViewById(R.id.back_edit);
        ok_img = (ImageView) findViewById(R.id.ok);
        toux = (ImageView) findViewById(R.id.toux);
        sex_nan1 = (ImageView) findViewById(R.id.sex_nan1);
        sex_nv = (ImageView) findViewById(R.id.sex_nv);
        xc_img = (ImageView) findViewById(R.id.xc_img);
        xj_img = (ImageView) findViewById(R.id.xj_img);
        cancel = (TextView) findViewById(R.id.cancel);
        cancel_icon = (TextView) findViewById(R.id.cancel_icon);


        edit_name.setOnClickListener(this);
        edit_geyan.setOnClickListener(this);
        number_base.setOnClickListener(this);
        sex_layout.setOnClickListener(this);
        bj_layout.setOnClickListener(this);
        day_layout.setOnClickListener(this);
        back_edit.setOnClickListener(this);
        ok_img.setOnClickListener(this);
        toux.setOnClickListener(this);
        cancel.setOnClickListener(this);
        sex_nv.setOnClickListener(this);
        sex_nan1.setOnClickListener(this);
        xc_img.setOnClickListener(this);
        xj_img.setOnClickListener(this);
        layout_icon.setOnClickListener(this);
        cancel_icon.setOnClickListener(this);
        user_te1.setOnClickListener(this);
        user_te2.setOnClickListener(this);
        setTextChange();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_name:
                break;
            case R.id.sex_layout: //性别
                setUserSex();
                break;
            case R.id.sex_nan1:
                setUserSex();
                sex_user.setText("男");
                break;
            case R.id.sex_nv:
                setUserSex();
                sex_user.setText("女");
                break;
            case R.id.cancel: //取消性别
                setUserSex();
                break;
            case R.id.bj_layout: //显示性别面板
                setUserSex();
                break;
            case R.id.day_layout: //设置生日
                setUsrlTime();
                break;
            case R.id.back_edit: //退出编辑提示
                alertUserBack();
                break;
            case R.id.ok: //保存编辑
                Intent i = new Intent();
                i.putExtra("UserSex", sex_user.getText().toString());
                i.putExtra("UserName",edit_name.getText().toString());
                i.putExtra("UserDay", day_data.getText().toString());
                i.putExtra("edit_geyan", edit_geyan.getText().toString());
                i.putExtra("url", userUrl.toString());

                setResult(3, i);
                finish();
                break;
            case R.id.xc_img: //相册
                setPhotoUserHead();
                break;
            case R.id.xj_img://相机
                setCameraUserHead();
                break;
            case R.id.user_te1: //相册
                setPhotoUserHead();
                break;
            case R.id.user_te2://相机
                setCameraUserHead();
                break;
            case R.id.toux: //头像面板
                setLayoutVisibility();
                break;
            case R.id.cancel_icon:
                setLayoutVisibility();
                break;
            case R.id.layout_icon:
                setLayoutVisibility();
                break;

        }
    }
    //设置设置头像布局显示
    private void setLayoutVisibility(){
        if(layout_icon.getVisibility()==View.VISIBLE){
            layout_icon.setVisibility(View.GONE);
        }else {
            layout_icon.setVisibility(View.VISIBLE);
        }
    }

    //相机
    private void setCameraUserHead(){
        try {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
        setLayoutVisibility();
        }catch (Exception e){
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
        }
    }

    //相册
    private void setPhotoUserHead(){
        try {
        // 激活系统图库，选择一张图片
        Intent intent1 = new Intent(Intent.ACTION_PICK);
        intent1.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
        setLayoutVisibility();
        }catch (Exception e){
            Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
        }
    }


    //字体数量监听
    private void setTextChange(){
        edit_geyan.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                number_base.setText(editable.length()+"/32");
                if(editable.length()>=32){
                    Toast.makeText(EditorActivity.this, "无法继续输入", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    //上传头像
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //上传头像
        setImgByStr(imageString);
    }
    /**
     * 上传头像       此处使用用的OKHttp post请求上传的图片
     * @param imgStr
     */
    public  void setImgByStr(String imgStr) {
        String url = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "参数值");//
        params.put("data", imgStr);
    }



    private void alertUserBack() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否退出本次编辑");
        builder.setTitle("提醒");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    //控制性别面板显示隐藏
    private void setUserSex() {
        if (bj_layout.getVisibility() == View.VISIBLE) {
            bj_layout.setVisibility(View.GONE);
        } else {
            bj_layout.setVisibility(View.VISIBLE);
        }
    }

    //设置用户生日
    private void setUsrlTime() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.datepicker, null);
        final DatePicker datePicker = view.findViewById(R.id.date_picker);
        //设置日期简略显示 否则详细显示 包括:星期\周
        datePicker.setCalendarViewShown(false);
        datePicker.init(1990, 01,
                01, null);
        //设置date布局
        builder.setView(view);
        builder.setTitle("设置日期信息");
        builder.setPositiveButton("确  定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                StringBuffer sb = new StringBuffer();
                sb.append(String.format("%d-%02d-%02d",
                        datePicker.getYear(),
                        datePicker.getMonth() + 1,
                        datePicker.getDayOfMonth()));
                day_data.setText(sb);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
                userUrl =uri.toString();
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
                userUrl =Uri.fromFile(tempFile).toString();
            } else {
                Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] bytes=baos.toByteArray();
                Glide.with(this).load(bytes)
                        .apply(new RequestOptions()
                                .skipMemoryCache(true) // 不使用内存缓存
                                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用磁盘缓存
                                .placeholder(R.mipmap.ic_launcher).centerCrop()
                                .transform(new GlideCircleTransform(EditorActivity.this, 3, Color.parseColor("#dddddd"))))
                        .into(toux);
            }
            try {
//                if (tempFile.exists() && tempFile.isFile()) {
//                    tempFile.delete();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (bj_layout.getVisibility() == View.VISIBLE) {
                bj_layout.setVisibility(View.GONE);
                return true;
            }
            alertUserBack();
        }
        return super.onKeyDown(keyCode, event);
    }
}
