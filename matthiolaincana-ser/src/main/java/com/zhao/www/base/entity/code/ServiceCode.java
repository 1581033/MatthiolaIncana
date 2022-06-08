package com.zhao.www.base.entity.code;

/**
 * @author Matthiola incana
 * @create 2021/10/13 9:12
 */
public enum ServiceCode {

    /**
     * 后端数据返回错误
     */
    ERROR(false,100,"error"),
    /**
     * 后端数据返回成功
     */
    SUCCESS(true,200,"success"),
    /**
     * 后端异常全局返回
     */
    EXCEPTION(false,1000,"exception"),
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


    private Boolean success;

    private Integer code;

    private String message;

    ServiceCode(Boolean success, Integer code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

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
