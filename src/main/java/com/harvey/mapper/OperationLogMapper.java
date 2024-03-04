package com.harvey.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harvey.domain.OperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {
}
