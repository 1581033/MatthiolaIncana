package com.zhao.www.base.entity.code;

/**
 * @author Matthiola incana
 * @create 2022/6/8 10:40
 */
public enum ServiceExceptionEnum {

    /**
     * 后端异常全局返回
     */
    EXCEPTION(false,1000,"出现其他异常！"),
    /**
     * 后端数据返回错误
     */
    NOTLOGGEDIN(false,100,"未登录，请重新登录！"),
    /**
     * 后端数据返回错误
     */
    INSUFFICIENTPERMISSIONS(false,100,"权限不足！"),
    /**
     * 后端数据返回错误
     */
    TOKENFAILEDVALIDATION(false,100,"密钥验证失败，请重新登录！"),
    /**
     * RRDIS错误返回异常
     */
    REDISCONNECTIONFAILURE(false, 100,"Redis连接异常！"),
    /**
     * 空指针错误返回异常
     */
    NUllPOINTEREXCEPTION(false, 100, "数据空指针异常！" );

    ServiceExceptionEnum(Boolean success, Integer code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private final Boolean success;

    private final Integer code;

    private final String message;

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
