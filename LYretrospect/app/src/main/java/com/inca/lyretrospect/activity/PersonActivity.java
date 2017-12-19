package com.inca.lyretrospect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inca.administrator.opensourcelibrary.utils.PreferenceUtil;
import com.inca.administrator.opensourcelibrary.utils.ToastUtils;
import com.inca.lyretrospect.R;
import com.inca.lyretrospect.global.TFBaseActivity;

/**
 * Created by Administrator on 2017/12/18.
 */

public class PersonActivity extends TFBaseActivity implements View.OnClickListener{
    private LinearLayout ll_xtsz;
    private TextView tv_login_exit,tv_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        super.setCustomerTitle(true,true,"个人中心");
    }

    @Override
    public void initSubViews() {
        ll_xtsz=(LinearLayout) findViewById(R.id.ll_xtsz);
        tv_login_exit=(TextView) findViewById(R.id.tv_login_exit);
        tv_name=(TextView) findViewById(R.id.tv_name);
    }

    @Override
    public void initData() {
        String employeename = PreferenceUtil.readString(this,"employeename");
        tv_name.setText(employeename);
    }

    @Override
    public void initEvent() {
        ll_xtsz.setOnClickListener(this);
        tv_login_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_xtsz :
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case  R.id.tv_login_exit:
                doDialog();
                break;
        }
    }
}
