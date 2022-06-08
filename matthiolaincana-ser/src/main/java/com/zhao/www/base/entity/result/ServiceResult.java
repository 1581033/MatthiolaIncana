package com.zhao.www.base.entity.result;

import com.zhao.www.base.entity.code.ServiceCode;
import com.zhao.www.base.entity.code.ServiceErrorEnum;
import com.zhao.www.base.entity.code.ServiceExceptionEnum;
import com.zhao.www.base.entity.code.ServiceSuccessEnum;
import com.zhao.www.utils.json.JsonUtil;
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
    private T result;
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

    public ServiceResult(ServiceCode serviceCode,T result){
        this(serviceCode.getSuccess(),serviceCode.getCode(),serviceCode.getMessage(),result);
    }

    public ServiceResult(Boolean success,Integer code,String message,T result){
        this(success,code,message,result,new Date());
    }

    public ServiceResult(Boolean success, Integer code, String message, T result, Date timestamp) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
        this.timestamp = timestamp;
    }

    public static <T> ServiceResult<T> success(T result) {
        return success(result,ServiceCode.SUCCESS.getMessage());
    }

    public static <T> ServiceResult<T> success(T result, String message) {
        return success(result,ServiceCode.SUCCESS.getCode(),message);
    }

    public static <T> ServiceResult<T> success(T result, Integer code, String message) {
        return new ServiceResult<T>(ServiceCode.SUCCESS.getSuccess(),code,message,result);
    }

    public static <T> ServiceResult<T> success(T result, ServiceCode serviceCode) {
        return new ServiceResult<T>(serviceCode,result);
    }

    public static <T> ServiceResult<T> error() {
        return error(ServiceCode.ERROR.getMessage());
    }

    public static <T> ServiceResult<T> error(String message) {
        return error(message,null);
    }

    public static <T> ServiceResult<T> error(String message,T result) {
        return new ServiceResult<T>(ServiceCode.ERROR.getSuccess(),ServiceCode.ERROR.getCode(),message,result);
    }

    public static <T> ServiceResult<T> error(ServiceCode serviceCode) {
        return new ServiceResult<T>(serviceCode,null);
    }

    public static <T> ServiceResult<T> error(ServiceCode serviceCode,T result) {
        return new ServiceResult<T>(serviceCode,result);
    }

    public static <T> ServiceResult<T> exception(ServiceCode serviceCode,T result) {
        return new ServiceResult<T>(serviceCode,result);
    }

    public static <T> void requestSuccess(HttpServletResponse response, ServiceCode serviceCode,T result){
        requestSuccess(response,serviceCode,result,"application/json");
    }

    public static <T> void requestSuccess(HttpServletResponse response, ServiceCode serviceCode,T result,String contentType){
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ){
            writer.write(JsonUtil.toJSONStringWithDateFormat(ServiceResult.success(result,serviceCode)));
            writer.flush();
        }catch (IOException e){
            requestError(response,ServiceCode.ERROR,500);
        }
    }

    public static void requestError(HttpServletResponse response, ServiceCode serviceCode,int status){
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status);
        try (
                PrintWriter writer = response.getWriter();
                ){
            writer.write(JsonUtil.toJSONStringWithDateFormat(ServiceResult.error(serviceCode)));
            writer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ServiceResult{" + "success=" + success + ", code=" + code + ", message=" + message + ", result=" + result + ", timestamp=" + timestamp + '}';
    }

}
