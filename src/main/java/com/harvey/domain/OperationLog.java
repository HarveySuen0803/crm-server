package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@TableName(value ="operation_log")
@Data
public class OperationLog implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    
    private Integer userId;
    
    private String description;
    
    private String result;
    
    @Serial
    private static final long serialVersionUID = 1L;
}
