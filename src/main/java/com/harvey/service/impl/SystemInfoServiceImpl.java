package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.SystemInfo;
import com.harvey.service.SystemInfoService;
import com.harvey.mapper.SystemInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoMapper, SystemInfo> implements SystemInfoService {
}




