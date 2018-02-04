package com.youxinpai.common.util.web;

/**
 * 统一的web返回对象格式
 *
 * @param <T>
 */
public class ResponseResult<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult() {
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult successed(Object data) {
        ResponseResult<Object> result = new ResponseResult<>();
        result.setCode(1);
        result.setMessage("请求成功");
        result.setData(data);
        return result;
    }

    public static ResponseResult failed() {
        ResponseResult<Object> result = new ResponseResult<>(0, "请求失败");
        return result;
    }

    public static ResponseResult failed(String message) {
        ResponseResult<Object> result = new ResponseResult<>(0, message);
        return result;
    }

    public static ResponseResult exception(Exception e) {
        ResponseResult<Object> result = new ResponseResult<>(-1, "程序异常", e.getStackTrace());
        return result;
    }

}
