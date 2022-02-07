package com.truman.BackgroundSystem.Common.Result;

public class ResultUtils {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ReturnCodes.SUCCESS.getCode());
        result.setMsg(ReturnCodes.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result Err(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
