package com.inca.lyretrospect;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.inca.administrator.opensourcelibrary.http.TFFailuredListener;
import com.inca.administrator.opensourcelibrary.http.TFSuccessListener;
import com.inca.administrator.opensourcelibrary.utils.LoadingDialog;
import com.inca.administrator.opensourcelibrary.utils.PreferenceUtil;
import com.inca.administrator.opensourcelibrary.utils.ToastUtils;
import com.inca.lyretrospect.activity.WebViewActivity;
import com.inca.lyretrospect.global.TFBaseActivity;
import com.inca.lyretrospect.http.TFRequest;


public class LoginActivity extends TFBaseActivity implements View.OnClickListener {
    private EditText uerName, userPsd;
    private TextView tv_login_btn;
    //    private List<GKElementEntity> data_list;
//    private PopWindow_Check popWindow_Check;
    private ArrayAdapter<String> arr_adapter;
    //    private GKElementEntity mGKElementEntity;
    private int width;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        super.setCustomerTitle(false, true, "登录");
    }

    @Override
    public void initSubViews() {
        String user = PreferenceUtil.readString(this, "user");
        String psd = PreferenceUtil.readString(this, "psd");
        uerName = (EditText) findViewById(R.id.uerName);
        userPsd = (EditText) findViewById(R.id.userPsd);
        uerName.setText(user);
        userPsd.setText(psd);
        tv_login_btn = (TextView) findViewById(R.id.tv_login_btn);
    }

    @Override
    public void initData() {
        //数据
//        data_list = new ArrayList<GKElementEntity>();

    }

    @Override
    public void initEvent() {
        tv_login_btn.setOnClickListener(this);
    }

    private void getElement(String user, String psd) {
//        GKUserRequest.getElelment(user, psd, this, new 1TFSuccessListener() {
//            @Override
//            public void onRespone(String msg, Object response) {
//                if (response != null) {
//                    data_list.clear();
//                    List<GKElementEntity> elementEntities =(List<GKElementEntity>) response;
//                    data_list.addAll(elementEntities);
//                    //保存单元列表
//                    BeanManager.getInstance().setElementEntities(data_list);
//                    popWindow_Check = new PopWindow_Check(LoginActivity.this, 600);
//                    ListView listView = popWindow_Check.getAllItemView();
//                    popWindow_Check.showAsDropDown(tv_check, 0, 0);
//                    MendianAdapter adapter = new MendianAdapter(LoginActivity.this, data_list);
//                    listView.setAdapter(adapter);
//                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                            ToastUtil_Big.showToast(data_list.get(position).getEntryname());
//                            mGKElementEntity = data_list.get(position);
//                            //保存选中的单元
//                            BeanManager.getInstance().setGkElementEntity(mGKElementEntity);
//                            tv_check.setText(data_list.get(position).getEntryname());
//                            popWindow_Check.dismiss();
//                        }
//                    });
//                    GKLoadingDialog.cancle();
//                }
//            }
//        }, new GKFailuredListener() {
//            @Override
//            public void onRespone(String msg, int errorCode) {
//                showToast(msg);
//                GKLoadingDialog.cancle();
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        String user = uerName.getText().toString().trim();
        String psd = userPsd.getText().toString().trim();
        switch (view.getId()) {
            case R.id.tv_login_btn:
                if (TextUtils.isEmpty(user)) {
                    ToastUtils.showToast(this, "账号号不能为空");
                    return;
                }
//                else if (TextUtils.isEmpty(psd)) {
//                    ToastUtils.showToast(this,"密码不能为空");
//                    return;
//                }
                else {
                    LoadingDialog.show(this, "登录中", true);
                    PreferenceUtil.write(LoginActivity.this, "user", user);
                    PreferenceUtil.write(LoginActivity.this, "psd", psd);
                    TFRequest.doLogin(user, psd, this, new TFSuccessListener() {
                        @Override
                        public void onRespone(String msg, Object response) {
                            String name = (String)response;
                            PreferenceUtil.write(LoginActivity.this,"employeename",name);
                            startActivity(new Intent(LoginActivity.this, WebViewActivity.class));
                            LoadingDialog.cancle();
                        }
                    }, new TFFailuredListener() {
                        @Override
                        public void onRespone(String msg, int errorCode) {
                            LoadingDialog.cancle();
                            ToastUtils.showToast(LoginActivity.this, msg);
                        }
                    });
                }
                break;
        }
    }


    private void loginSuccess() {
//        Intent intent = new Intent();
//        intent.setClass(this, GKMainActivity.class);
//        startActivity(intent);
//        finish();
    }
}
