package yjh.com.cn.pearlvideo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import yjh.com.cn.pearlvideo.R;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        inItView();


    }

    private void inItView(){
        back_view= (ImageView) findViewById(R.id.fh);
        back_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fh:
                finish();
                break;
        }

    }
}
