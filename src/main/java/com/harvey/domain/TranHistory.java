package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName tran_history
 */
@TableName(value ="tran_history")
@Data
public class TranHistory implements Serializable {
    private Integer id;

    private Integer tranId;

    private Integer stage;

    private BigDecimal money;

    private Date expectedDate;

    private Date createTime;

    private Integer createBy;

    private static final long serialVersionUID = 1L;
}