package com.harvey.service;

import com.harvey.common.Result;
import com.harvey.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harvey.dto.PageDto;

public interface ActivityService extends IService<Activity> {
    Result getActivityByPage(PageDto pageDto);
    
}
