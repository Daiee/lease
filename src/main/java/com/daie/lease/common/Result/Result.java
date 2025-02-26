package com.daie.lease.common.Result;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        assert data != null;
        result.setData(data);
        return result;
    }

    private static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(data);
        return result.setCode(resultCodeEnum.getCode()).setMsg(resultCodeEnum.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> success() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> fail(T data, ResultCodeEnum resultCodeEnum) {
        return build(data, resultCodeEnum);
    }

    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }
}
