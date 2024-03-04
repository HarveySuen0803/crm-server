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
 * @TableName product
 */
@TableName(value ="product")
@Data
public class Product implements Serializable {
    private Integer id;

    private String name;

    private BigDecimal guidePriceS;

    private BigDecimal guidePriceE;

    private BigDecimal quotation;

    private Integer state;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}