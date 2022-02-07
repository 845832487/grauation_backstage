package com.truman.BackgroundSystem.Common.Result;


public enum ReturnCodes {
    SUCCESS(666, "成功"),
    UNKNOWN_ERROR(-1, "未知错误"),
    USER_IS_EXIST(-2, "用户已存在"),
    USER_NOT_EXIST(-3, "用户不存在"),
    DATA_IS_NULL(-4, "用户为空"),
    ;
    private Integer code;
    private String msg;

    ReturnCodes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
