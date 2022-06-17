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
     * 后端数据登录成功
     */
    LOGINSUCCESS(true,200,"登陆成功！"),
    /**
     * 后端数据退出登录成功
     */
    LOGINOUTSUCCESS(true,200,"退出成功！"),
    /**
     * 后端数据登录验证错误
     */
    LOGINDISABLED(true,200,"该用户已被禁用！"),
    /**
     * 后端数据登录验证错误
     */
    LOGINLOCKED(true,200,"该用户已被锁定！"),
    /**
     * 后端数据登录验证错误
     */
    LOGINACCOUNT(true,200,"该用户已过期！"),
    /**
     * 后端数据登录验证错误
     */
    LOGINCREDENTIALS(true,200,"该用户证书过期！"),
    /**
     * 后端数据登录验证错误
     */
    LOGINFAILURE(false,100,"账号或密码错误！"),
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
    NUllPOINTEREXCEPTION(false, 100, "数据空指针异常！" ),
    /**
     * 请求参数空错误返回异常
     */
    HTTPMESSAGENOTREADABLEEXCEPTION(false, 100, "请求参数空异常！"),
    /**
     * 校验数据异常错误返回异常
     */
    METHODARGUMENTNOTVALIDEXCEPTION(false, 100, "校验参数异常！");


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
