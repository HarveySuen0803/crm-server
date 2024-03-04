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
 * @TableName tran
 */
@TableName(value ="tran")
@Data
public class Tran implements Serializable {
    private Integer id;

    private String tranNo;

    private Integer customerId;

    private BigDecimal money;

    private Date expectedDate;

    private Integer stage;

    private String description;

    private Date nextContactTime;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}