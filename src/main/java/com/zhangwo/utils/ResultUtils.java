package com.zhangwo.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {
    //登录
    public final static Map<String, Object> success(String jwtToken , Object data ,int code) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("jwtToken", jwtToken);
        map.put("code", code);
        map.put("data", data);
        map.put("success", true);
        return map;
    }
}
