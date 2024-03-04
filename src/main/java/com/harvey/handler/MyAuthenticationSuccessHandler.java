package com.harvey.handler;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harvey.common.HttpConstant;
import com.harvey.common.RedisKey;
import com.harvey.common.Result;
import com.harvey.domain.User;
import com.harvey.util.ResponseUtil;
import com.harvey.vo.LoginVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        
        User userDetails = new User();
        userDetails.setId(user.getId());
        userDetails.setRoleId(user.getRoleId());
        userDetails.setUsername(user.getUsername());
        userDetails.setAuthNameList(user.getAuthNameList());
        
        Long ttl = Boolean.parseBoolean(request.getParameter("remember")) ? RedisKey.LOGIN_TOKEN_L_TTL : RedisKey.LOGIN_TOKEN_S_TTL;
        
        HashMap<String, Object> jwtPayload = new HashMap<>();
        jwtPayload.put(JWTPayload.ISSUED_AT, DateTime.now());
        jwtPayload.put(JWTPayload.EXPIRES_AT, DateTime.now().offset(DateField.MONTH, 1));
        jwtPayload.put(JWTPayload.NOT_BEFORE, DateTime.now());
        jwtPayload.put("userDetails", JSONUtil.toJsonStr(userDetails));
        String token = JWTUtil.createToken(jwtPayload, HttpConstant.LOGIN_TOKEN_KEY);
        
        redisTemplate.opsForValue().set(RedisKey.LOGIN_TOKEN + "::" + user.getId(), token, ttl, RedisKey.LOGIN_TOKEN_TTL_UNIT);
        
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setUserId(user.getId());
        loginVo.setUsername(user.getUsername());
        
        ResponseUtil.write(response, Result.success(loginVo));
    }
}
