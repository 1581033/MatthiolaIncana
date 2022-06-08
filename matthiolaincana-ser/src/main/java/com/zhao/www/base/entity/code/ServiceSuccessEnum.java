package com.zhao.www.base.entity.code;

/**
 * @author Matthiola incana
 * @create 2022/6/8 11:03
 */
public enum ServiceSuccessEnum {

    SUCCESS(true,200,"success");

    private final Boolean success;

    private final Integer code;

    private final String message;

    ServiceSuccessEnum(Boolean success, Integer code, String message){
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
