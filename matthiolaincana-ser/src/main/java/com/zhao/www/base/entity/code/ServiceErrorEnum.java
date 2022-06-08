package com.zhao.www.base.entity.code;

/**
 * @author Matthiola incana
 * @create 2022/6/8 11:05
 */
public enum ServiceErrorEnum {

    /**
     * 后端数据返回错误
     */
    ERROR(false,100,"error");

    private final Boolean success;

    private final Integer code;

    private final String message;

    ServiceErrorEnum(Boolean success, Integer code, String message){
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
