package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.TranHistory;
import com.harvey.service.TranHistoryService;
import com.harvey.mapper.TranHistoryMapper;
import org.springframework.stereotype.Service;

@Service
public class TranHistoryServiceImpl extends ServiceImpl<TranHistoryMapper, TranHistory> implements TranHistoryService {
}




