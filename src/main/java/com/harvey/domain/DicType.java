package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName dic_type
 */
@TableName(value ="dic_type")
@Data
public class DicType implements Serializable {
    private Integer id;

    private String typeCode;

    private String typeName;

    private String remark;

    private static final long serialVersionUID = 1L;
}