package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName auth
 */
@TableName(value ="auth")
@Data
public class Auth implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}