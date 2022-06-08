package com.zhao.www.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author Matthiola incana
 * @create 2021/10/21 12:05
 */
public class JsonUtil {

    private final static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public JsonUtil() {
    }

    public static String toJSONStringWithDateFormat(Object object){
        return JSON.toJSONStringWithDateFormat(object,DATEFORMAT, SerializerFeature.WriteDateUseDateFormat);
    }
}
