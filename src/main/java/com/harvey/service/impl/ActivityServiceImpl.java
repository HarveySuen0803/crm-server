package com.harvey.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.harvey.common.Result;
import com.harvey.domain.Activity;
import com.harvey.domain.User;
import com.harvey.dto.PageDto;
import com.harvey.mapper.ActivityMapper;
import com.harvey.service.ActivityService;
import com.harvey.vo.ActivityVo;
import com.harvey.vo.PageVo;
import com.harvey.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {
    @Override
    public Result getActivityByPage(PageDto pageDto) {
        Page<Activity> page = new Page(pageDto.getPageNo(), pageDto.getPageSize());
        Db.lambdaQuery(Activity.class)
            .page(page);
        
        List<ActivityVo> activityVoList = page.getRecords().stream().map((activity) -> {
            ActivityVo activityVo = BeanUtil.copyProperties(activity, ActivityVo.class);
            User user = Db.lambdaQuery(User.class)
                .select(User::getUsername)
                .eq(User::getId, activity.getUserId())
                .one();
            activityVo.setUsername(user.getUsername());
            return activityVo;
        }).toList();
        
        PageVo<ActivityVo> pageVo = new PageVo<>();
        pageVo.setList(activityVoList);
        pageVo.setTotalSize(page.getTotal());
        pageVo.setTotalPages(page.getPages());
        
        return Result.success(pageVo);
    }
}




