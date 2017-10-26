package com.commons.web;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author :  pengqingsong
 * Date : 02/03/2017 11:48
 * Description : 通用的响应封装类
 * Test :
 */
@Data
public class GeneralResponse<T> implements Serializable {

    private static final String SUCCESS_MSG = "success";
    private static final String UNKNOWN_EXCEPTION = "unknown exception";
    private static final String NO_PRIVILEGE_MSG = "您无权进行此操作";

    private static final GeneralResponse EMPTY_SUCCESS_RESPONSE = new GeneralResponse(SUCCESS_MSG);
    private static final GeneralResponse NO_PRIVILEGE_RESPONSE = new GeneralResponse(NO_PRIVILEGE_MSG);
    /**
     * 辅助消息，如果为success表示请求处理成功，否则说明请求处理不成功
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    private GeneralResponse() {
    }

    private GeneralResponse(String msg) {
        this.msg = msg;
    }

    public static <T> GeneralResponse<T> successResponse() {
        return EMPTY_SUCCESS_RESPONSE;
    }

    public static <T> GeneralResponse<T> noPrivilegeResponse() {
        return NO_PRIVILEGE_RESPONSE;
    }

    public static <T> GeneralResponse<T> successResponse(T data) {
        GeneralResponse response = new GeneralResponse();
        response.setMsg(SUCCESS_MSG);
        response.setData(data);
        return response;
    }

    public static <T> GeneralResponse<T> failedResponse(String msg) {
        GeneralResponse response = new GeneralResponse();
        response.setMsg(msg);
        return response;
    }

    public static <T> GeneralResponse<T> unknownExceptionResponse(Throwable e) {
        GeneralResponse response = new GeneralResponse();
        response.setMsg(e.getMessage());
        return response;
    }

    public static boolean isSuccess(GeneralResponse response) {
        return response != null && Objects.equals(SUCCESS_MSG, response.getMsg());
    }
}
