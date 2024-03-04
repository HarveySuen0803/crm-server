package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName customer_remark
 */
@TableName(value ="customer_remark")
@Data
public class CustomerRemark implements Serializable {
    private Integer id;

    private Integer customerId;

    private Integer noteWay;

    private String noteContent;

    private Integer createBy;

    private Date createTime;

    private Date editTime;

    private Integer editBy;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}