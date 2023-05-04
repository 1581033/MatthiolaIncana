package com.matth.entity.result;

import com.matth.entity.code.ServiceCode;
import com.matth.utils.json.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Matthiola incana
 */
@Getter
@Setter
public class ServiceResult<T> implements Serializable {

    /**
     * 判断是否开发人员主动发起失败
     */
    private Boolean success;
    /**
     * 判断是否Java代码出现异常
     */
    private Integer code;
    /**
     * 统一消息字段
     */
    private String message;
    /**
     * 传输的对象数据
     */
    private T data;
    /**
     * 时间戳
     */
    private Date timestamp;
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 声明public
     * 方便二进制序列化反序列化构建
     */
    public ServiceResult() { }

    public ServiceResult(ServiceCode serviceCode, T data){
        this(serviceCode.getSuccess(),serviceCode.getCode(),serviceCode.getMessage(),data);
    }

    public ServiceResult(Boolean success,Integer code,String message,T data){
        this(success,code,message,data,new Date());
    }

    public ServiceResult(Boolean success, Integer code, String message, T data, Date timestamp) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    public static <T> ServiceResult<T> success(T data) {
        return success(data,ServiceCode.SUCCESS.getMessage());
    }

    public static <T> ServiceResult<T> success(T data, String message) {
        return success(data,ServiceCode.SUCCESS.getCode(),message);
    }

    public static <T> ServiceResult<T> success(T data, Integer code, String message) {
        return new ServiceResult<T>(true,code,message,data);
    }

    public static <T> ServiceResult<T> success(T data, ServiceCode serviceCode) {
        return new ServiceResult<T>(serviceCode,data);
    }

    public static <T> ServiceResult<T> error() {
        return error(ServiceCode.ERROR.getMessage());
    }

    public static <T> ServiceResult<T> error(String message) {
        return error(message,null);
    }

    public static <T> ServiceResult<T> error(String message,T data) {
        return new ServiceResult<T>(ServiceCode.ERROR.getSuccess(),ServiceCode.ERROR.getCode(),message,data);
    }

    public static <T> ServiceResult<T> error(ServiceCode serviceCode) {
        return new ServiceResult<T>(serviceCode,null);
    }

    public static <T> ServiceResult<T> error(ServiceCode serviceCode,T data) {
        return new ServiceResult<T>(serviceCode,data);
    }

    public static <T> ServiceResult<T> exception(ServiceCode serviceCode,T data) {
        return new ServiceResult<T>(serviceCode,data);
    }

    public static <T> void requestSuccess(HttpServletResponse response, ServiceCode serviceCode){
        requestSuccess(response,serviceCode,"application/json");
    }

    public static <T> void requestSuccess(HttpServletResponse response, ServiceCode serviceCode,String contentType){
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ){
            writer.write(JsonUtil.toJSONStringWithDateFormat(ServiceResult.success(null,serviceCode)));
            writer.flush();
        }catch (IOException e){
            requestError(response,ServiceCode.ERROR,HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public static void requestError(HttpServletResponse response, ServiceCode serviceCode){
        requestError(response,serviceCode,HttpServletResponse.SC_OK);
    }

    public static void requestError(HttpServletResponse response, ServiceCode serviceCode,int status){
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status);
        try (
                PrintWriter writer = response.getWriter()
        ){
            writer.write(JsonUtil.toJSONStringWithDateFormat(ServiceResult.error(serviceCode)));
            writer.close();
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ServiceResult{" + "success=" + success + ", code=" + code + ", message=" + message + ", data=" + data + ", timestamp=" + timestamp + '}';
    }

}
