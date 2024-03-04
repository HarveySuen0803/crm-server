package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName dic_value
 */
@TableName(value ="dic_value")
@Data
public class DicValue implements Serializable {
    private Integer id;

    private String typeCode;

    private String typeValue;

    private Integer order;

    private String remark;

    private static final long serialVersionUID = 1L;
}