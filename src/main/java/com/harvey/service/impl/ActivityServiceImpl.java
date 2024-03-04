package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.Activity;
import com.harvey.service.ActivityService;
import com.harvey.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
}




