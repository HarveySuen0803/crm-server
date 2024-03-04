package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.RoleAuth;
import com.harvey.service.RoleAuthService;
import com.harvey.mapper.RoleAuthMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthServiceImpl extends ServiceImpl<RoleAuthMapper, RoleAuth> implements RoleAuthService {
}




