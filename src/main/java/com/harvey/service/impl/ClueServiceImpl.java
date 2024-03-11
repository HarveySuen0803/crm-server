package com.harvey.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.harvey.common.Result;
import com.harvey.domain.Activity;
import com.harvey.domain.Clue;
import com.harvey.domain.User;
import com.harvey.dto.PageDto;
import com.harvey.service.ClueService;
import com.harvey.mapper.ClueMapper;
import com.harvey.vo.ActivityVo;
import com.harvey.vo.ClueVo;
import com.harvey.vo.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Override
    public Result getClueByPage(PageDto pageDto) {
        Page<Clue> page = new Page(pageDto.getPageNo(), pageDto.getPageSize());
        Db.lambdaQuery(Clue.class)
            .page(page);
        
        List<ClueVo> clueVoList = page.getRecords().stream().map((clue) -> {
            ClueVo clueVo = BeanUtil.copyProperties(clue, ClueVo.class);
            User user = Db.lambdaQuery(User.class)
                .select(User::getUsername)
                .eq(User::getId, clue.getUserId())
                .one();
            clueVo.setUsername(user.getUsername());
            return clueVo;
        }).toList();
        
        PageVo<ClueVo> pageVo = new PageVo<>();
        pageVo.setList(clueVoList);
        pageVo.setTotalSize(page.getTotal());
        pageVo.setTotalPages(page.getPages());
        
        return Result.success(pageVo);
    }
}




