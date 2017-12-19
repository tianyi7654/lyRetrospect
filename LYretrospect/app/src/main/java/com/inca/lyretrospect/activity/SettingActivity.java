package com.inca.lyretrospect.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.inca.administrator.opensourcelibrary.utils.ToastUtils;
import com.inca.lyretrospect.R;
import com.inca.lyretrospect.global.TFBaseActivity;
import com.inca.lyretrospect.utils.DataCleanManager;

import java.io.File;

/**
 * Created by Administrator on 2017/12/18.
 */

public class SettingActivity extends TFBaseActivity implements View.OnClickListener{
    private TextView tv_clearCache;//清除缓存
    private TextView tv_cacheSize;//缓存大小
    private String sdpath, mSavePath;
    private File file;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sdpath = Environment.getExternalStorageDirectory() + "/";
        mSavePath = sdpath + "download";
        file = new File(mSavePath);
        super.setCustomerTitle(true,true,"用户设置");
    }

    @Override
    public void initSubViews() {
        tv_clearCache=(TextView) findViewById(R.id.tv_clearCache);
        tv_cacheSize=(TextView) findViewById(R.id.tv_cacheSize);
    }

    @Override
    public void initData() {
        // 获取缓存大小
        try {
            tv_cacheSize.setText( DataCleanManager.getFormatSize(DataCleanManager.getFolderSize(file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initEvent() {
        tv_clearCache.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_clearCache:
                DataCleanManager.cleanApplicationData(this);
                ToastUtils.showToast(this,"删除数据成功");
                try {
                    tv_cacheSize.setText( DataCleanManager.getFormatSize(DataCleanManager.getFolderSize(file)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
