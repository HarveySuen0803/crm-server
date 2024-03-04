package com.harvey.handler;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.harvey.common.*;
import com.harvey.domain.User;
import com.harvey.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutHandler implements LogoutHandler {
    @Autowired
    RedisTemplate redisTemplate;
    
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        JWT tokenJwt = JWT.of(request.getHeader(HttpHeader.AUTHORIZATION)).setKey(HttpConstant.LOGIN_TOKEN_KEY);
        
        // If the token is invalid or expired
        if (!tokenJwt.verify() || !tokenJwt.validate(0)) {
            ResponseUtil.write(response, Result.failure());
            return;
        }
        
        // Get user info from token
        User userDetails = JSONUtil.toBean(tokenJwt.getPayload("userDetails").toString(), User.class);
        
        redisTemplate.delete(RedisKey.LOGIN_TOKEN + "::" + userDetails.getId());
    }
}
