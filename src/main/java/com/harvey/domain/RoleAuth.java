package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName role_auth
 */
@TableName(value ="role_auth")
@Data
public class RoleAuth implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer authId;

    private static final long serialVersionUID = 1L;
}