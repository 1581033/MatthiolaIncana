package com.zhao.www.utils.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;


/**
 * @author Matthiola incana
 * @create 2021/10/21 12:05
 */
public class JsonUtil {

    private final static String DATEFORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public JsonUtil() {
    }

    public static String toJSONStringWithDateFormat(Object object){
        return JSON.toJSONString(object,DATEFORMAT, JSONWriter.Feature.PrettyFormat);
    }
}
