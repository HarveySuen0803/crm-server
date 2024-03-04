package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    private Integer id;

    private Integer userId;

    private String name;

    private Date startTime;

    private Date endTime;

    private BigDecimal cost;

    private String description;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}