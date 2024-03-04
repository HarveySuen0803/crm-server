package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName activity_remark
 */
@TableName(value ="activity_remark")
@Data
public class ActivityRemark implements Serializable {
    private Integer id;

    private Integer activityId;

    private String noteContent;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private Integer deleted;

    private static final long serialVersionUID = 1L;
}