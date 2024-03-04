package com.harvey.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.harvey.common.*;
import com.harvey.domain.User;
import com.harvey.util.ResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class MyTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // If the request is login, then skip the filter
        String requestURI = request.getRequestURI();
        if (requestURI.equals(HttpUri.LOGIN)) {
            filterChain.doFilter(request, response);
            return;
        }

        // If the request is logout, then skip the filter
        String token = request.getHeader(HttpHeader.AUTHORIZATION);
        if (StrUtil.isBlank(token)) {
            ResponseUtil.write(response, Result.unauthorized());
            return;
        }
        
        // If the token is invalid or expired
        JWT tokenJwt = JWT.of(token).setKey(HttpConstant.LOGIN_TOKEN_KEY);
        if (!tokenJwt.verify() || !tokenJwt.validate(0)) {
            ResponseUtil.write(response, Result.unauthorized());
            return;
        }
        
        // Get user info from token
        User userDetails = JSONUtil.toBean(tokenJwt.getPayload("userDetails").toString(), User.class);
        // System.out.println(userDetails.getAuthorities());
        
        // Todo: Token renew by XXL-JOB
        // threadPoolTaskExecutor.execute(() -> {
        //     redisTemplate.expire(RedisKey.LOGIN_TOKEN + userId, RedisKey.LOGIN_TOKEN_L_TTL, RedisKey.LOGIN_TOKEN_TTL_UNIT);
        // });
        
        // If the user is not authenticated, then authenticate the user
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        }
        
        filterChain.doFilter(request, response);
    }
}
