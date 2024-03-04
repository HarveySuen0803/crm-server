package com.harvey.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harvey.domain.CustomerRemark;
import com.harvey.service.CustomerRemarkService;
import com.harvey.mapper.CustomerRemarkMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerRemarkServiceImpl extends ServiceImpl<CustomerRemarkMapper, CustomerRemark> implements CustomerRemarkService {
}




