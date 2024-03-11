package com.harvey.controller;

import com.harvey.common.Result;
import com.harvey.dto.PageDto;
import com.harvey.service.ActivityService;
import com.harvey.service.ClueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private ClueService clueService;
    
    @PostMapping("/page")
    public Result getClueByPage(@RequestBody PageDto pageDto) {
        log.info("getActivityByPage, {}", pageDto);
        return clueService.getClueByPage(pageDto);
    }
}
