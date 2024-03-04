package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.Tran;
import com.harvey.service.TranService;
import com.harvey.mapper.TranMapper;
import org.springframework.stereotype.Service;

@Service
public class TranServiceImpl extends ServiceImpl<TranMapper, Tran> implements TranService {
}




