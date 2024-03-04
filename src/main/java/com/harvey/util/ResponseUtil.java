package com.harvey.util;

import cn.hutool.json.JSONUtil;
import com.harvey.common.Result;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResponseUtil {
    public static void write(HttpServletResponse response, Result result) {
        try {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
