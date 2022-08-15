package com.lzy.websocketonlinechat.utils;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author lzy
 * @className messageUtils
 * @date 2022/8/6 18:00
 * @description 封装系统消息
 */

public class Utils {

    public static String getMessage(boolean isSystemMessage, Map<Integer,String> fromUser, Object content){
        return null;
    }

    //获取当前时间
    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String now = formatter.format(System.currentTimeMillis());
        return now;
    }
}
