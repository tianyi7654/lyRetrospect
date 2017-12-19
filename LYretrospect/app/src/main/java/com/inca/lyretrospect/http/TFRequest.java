package com.inca.lyretrospect.http;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.inca.administrator.opensourcelibrary.http.TFFailuredListener;
import com.inca.administrator.opensourcelibrary.http.TFMobileHttptRequest;
import com.inca.administrator.opensourcelibrary.http.TFNetCallBack;
import com.inca.administrator.opensourcelibrary.http.TFSuccessListener;
import com.inca.lyretrospect.common.TFMobileConstants;
import com.inca.lyretrospect.common.UrlManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/12/15.
 */

public class TFRequest {
    /**
     * 用户登录接口
     * @param phoneNumber       手机号码
     * @param password          密码
     * @param successListener   成功回调
     * @param failuredListener  失败回调
     */
    public static void doLogin(String phoneNumber, String password, Context context, final TFSuccessListener successListener, final TFFailuredListener failuredListener){
        assert(successListener!=null);
        assert(failuredListener!=null);

        /** 组装请求参数 */
        RequestParams params = new RequestParams();

        try {
            params.put("employeeid" , phoneNumber);
            params.put("loginpwd", password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TFMobileHttptRequest.post(UrlManager.appLogin, params, context, new TFNetCallBack() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    /** 针对返回的业务数据会重新包装一遍再返回到View */
                    String msg = response.getString(TFMobileConstants.Response.MSG);
//
                    String code = response.optString(TFMobileConstants.Response.CODE);
                    if (code.equals("200")) {
                        String employeename = response.optJSONObject(TFMobileConstants.Response.DATA).optString("employeename");
                        /** 工具类json转为User实体 **/
//                         user = SPJsonUtil.fromJsonToModel(response.getJSONObject(TFMobileConstants.Response.RESULT), SPUser.class);
                        successListener.onRespone(msg, employeename);
                    }else{
                        failuredListener.onRespone(msg, -1);
                    }
                } catch (Exception e) {
                    successListener.onRespone(e.getMessage(), -1);
                }
            }
            @Override
            public void onFailure(int statusCode,  Throwable throwable, JSONObject errorResponse) {
                failuredListener.onRespone(throwable.getMessage(), statusCode);
            }

        });
    }
}
