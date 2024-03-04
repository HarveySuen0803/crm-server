package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.Auth;
import com.harvey.service.AuthService;
import com.harvey.mapper.AuthMapper;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {
}




