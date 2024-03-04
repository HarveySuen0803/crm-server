package com.harvey.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    public static Result success() {
        return new Result(200, "success", null);
    }

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result failure() {
        return new Result(400, "failure", null);
    }

    public static Result failure(Object data) {
        return new Result(400, "failure", data);
    }

    public static Result failure(String msg, Object data) {
        return new Result(400, msg, data);
    }

    public static Result failure(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result unauthorized() {
        return new Result(401, "unauthorized", null);
    }

    public static Result unauthorized(Object data) {
        return new Result(401, "unauthorized", data);
    }

    public static Result unauthorized(String msg, Object data) {
        return new Result(401, msg, data);
    }

    public static Result forbidden() {
        return new Result(403, "forbidden", null);
    }

    public static Result forbidden(Object data) {
        return new Result(403, "forbidden", data);
    }

    public static Result forbidden(String msg, Object data) {
        return new Result(403, msg, data);
    }

    public static Result notFound() {
        return new Result(404, "not found", null);
    }

    public static Result notFound(Object data) {
        return new Result(404, "not found", data);
    }

    public static Result notFound(String msg, Object data) {
        return new Result(404, msg, data);
    }

    public static Result internalServerError() {
        return new Result(500, "internal server error", null);
    }

    public static Result internalServerError(Object data) {
        return new Result(500, "internal server error", data);
    }

    public static Result internalServerError(String msg, Object data) {
        return new Result(500, msg, data);
    }

    public static Result badGateway() {
        return new Result(502, "bad gateway", null);
    }

    public static Result badGateway(Object data) {
        return new Result(502, "bad gateway", data);
    }

    public static Result badGateway(String msg, Object data) {
        return new Result(502, msg, data);
    }

    public static Result serviceUnavailable() {
        return new Result(503, "service unavailable", null);
    }

    public static Result serviceUnavailable(Object data) {
        return new Result(503, "service unavailable", data);
    }

    public static Result serviceUnavailable(String msg, Object data) {
        return new Result(503, msg, data);
    }

    public static Result gatewayTimeout() {
        return new Result(504, "gateway timeout", null);
    }

    public static Result gatewayTimeout(Object data) {
        return new Result(504, "gateway timeout", data);
    }

    public static Result gatewayTimeout(String msg, Object data) {
        return new Result(504, msg, data);
    }
}
