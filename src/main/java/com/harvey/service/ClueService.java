package com.harvey.service;

import com.harvey.common.Result;
import com.harvey.domain.Clue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harvey.dto.PageDto;

public interface ClueService extends IService<Clue> {
    Result getClueByPage(PageDto pageDto);
    
}
