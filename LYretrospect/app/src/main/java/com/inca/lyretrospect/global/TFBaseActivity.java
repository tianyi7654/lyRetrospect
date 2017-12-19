package com.inca.lyretrospect.global;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inca.lyretrospect.R;
import com.inca.lyretrospect.common.TFIViewController;
import com.inca.lyretrospect.view.MyDialog;

import java.util.Stack;


/**
 * Created by dtf on 2017/9/8.
 */

public abstract class TFBaseActivity extends FragmentActivity implements TFIViewController {
    public static TFBaseActivity instance = null;
    /** 用来保存�?有已打开的Activity */
    private static Stack<Activity> onLineActivityList = new Stack<Activity>();
    public boolean isCustomerTtitle ;	//是否自定义标题栏
    public boolean isBackShow ;			//是否显示返回箭头
    public boolean isDelete ;	//是否显示删除按钮
    private String mTtitle ;				//标题栏
    private LinearLayout ll_back;
    private TextView tv_title;
    private TextView  tv_right_titlebar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        instance=this;
        onLineActivityList.push(this);
        //图片加载配置
//        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.mipmap.load_fail_qiandaojilu)
//                .showImageOnFail(R.mipmap.load_fail_qiandaojilu)
//                .cacheInMemory(true).bitmapConfig(Bitmap.Config.RGB_565)
//                .build();
    }

    /**
     * 是否自定义标题 , 该方法必须在子Activity的 super.onCreate()之前调用, 否则无效
     * @param customerTtitle
     */
    public void setCustomerTitle(boolean backShow, boolean customerTtitle , String title){
        isCustomerTtitle = customerTtitle;
        isBackShow = backShow;
        mTtitle = title;
        init();
    }
    public void setTitle(String title){
        mTtitle = title;
        if (tv_title!=null)tv_title.setText(mTtitle);
    }
    public void setDelete(String title,boolean isDelete){
        if(isDelete){
            tv_right_titlebar.setText(title);
            tv_right_titlebar.setVisibility(View.VISIBLE);
        }else {
            tv_right_titlebar.setVisibility(View.GONE);
        }

    }
    /**
     * activity初始化
     *
     */
    public void init(){
        if (isCustomerTtitle){
            //设置标题为某个layout
//            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
            ll_back = (LinearLayout)findViewById(R.id.ll_back);
            if (isBackShow){
                ll_back.setVisibility(View.VISIBLE);
                ll_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TFBaseActivity.this.finish();
                    }
                });
            }else{
                if(ll_back!=null)ll_back.setVisibility(View.GONE);
            }
            tv_title = (TextView)findViewById(R.id.tv_title);
            tv_right_titlebar=(TextView)findViewById(R.id.tv_right_titlebar);
            if(tv_title!=null)tv_title.setText(mTtitle);
        }


        initSubViews();
        initData();
        initEvent();
    }
    public void doDialog() {
        MyDialog.Builder builder = new MyDialog.Builder(this);
        builder.setMessage("确定退出吗?");
        builder.setConfirmButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAll();
                dialog.dismiss();

            }
        });
        builder.setCancelButton("取消", new android.content.DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);
        MyDialog myDialog = builder.create();
        //取消监听
//        myDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                ToastUtil_Big.showToast("请点击完成按钮，保存该条记录");
//                dialog.dismiss();
//            }
//        });
        myDialog.show();
    }
    /**
     * 重新登录
     */
    @Override
    public void gotoLoginPage() {
//        Intent loginIntent = new Intent(this, LoginActivity.class);
//        this.startActivity(loginIntent);
    }
    /** 关闭所有Activity,注意：请已BaseActivity为父�? */
    protected static void finishAll() {
        int len = onLineActivityList.size();
        for (int i = 0; i < len; i++) {
            Activity activity = onLineActivityList.pop();
            activity.finish();
        }
    }
    /**
     * 以下三个函数不需要再子类调用, 只需要在子类的
     * onCrate()中调用:super.init()方法即可
     * 基类函数,初始化界面
     */
    abstract public void initSubViews();

    /**
     * 基类函数, 初始化数据
     */
    abstract public void initData();

    /**
     * 基类函数, 绑定事件
     */
    abstract public void initEvent();
//    public void showConfirmDialog(String message , String title , final GKConfirmDialog.ConfirmDialogListener confirmDialogListener , final int actionType){
//        GKConfirmDialog.Builder builder = new GKConfirmDialog.Builder(this);
//        builder.setMessage(message);
//        builder.setTitle(title);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                //设置你的操作事项
//                if(confirmDialogListener!=null)confirmDialogListener.clickOk(actionType);
//            }
//        });
//
//        builder.setNegativeButton("取消",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        builder.create().show();
//    }
}
