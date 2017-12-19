package com.inca.lyretrospect.common;

/**
 * Created by Administrator on 2017/12/15.
 */

public class TFMobileConstants {
    //数据返回常量key
    public class Response {
        public static final String RESULT = "result";
        public static final String MSG = "message";
        public static final String STATUS = "status";
        public static final String DATA = "data";
        public static final String CODE = "code";
        public static final int RESPONSE_CODE_TOEKN_EMPTY = -100;    //缺少token
        public static final int RESPONSE_CODE_TOEKN_EXPIRE = -101;    //token过期, 需要重新登录重新刷新token
        public static final int RESPONSE_CODE_TOEKN_INVALID = -102;    //token无效, 需要重新登录获取新token
    }
}
