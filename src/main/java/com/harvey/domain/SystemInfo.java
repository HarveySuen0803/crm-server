package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName system_info
 */
@TableName(value ="system_info")
@Data
public class SystemInfo implements Serializable {
    private Integer id;

    private String systemCode;

    private String name;

    private String site;

    private String logo;

    private String title;

    private String description;

    private String keywords;

    private String shortcuticon;

    private String tel;

    private String weixin;

    private String email;

    private String address;

    private String version;

    private String closemsg;

    private String isopen;

    private Date createTime;

    private Integer createBy;

    private Date editTime;

    private Integer editBy;

    private static final long serialVersionUID = 1L;
}