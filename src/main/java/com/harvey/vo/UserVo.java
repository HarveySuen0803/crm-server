package com.harvey.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String phone;
    
    private String email;
    
    private Integer isAccountNoExpired;
    
    private Integer isCredentialsNoExpired;
    
    private Integer isAccountNoLocked;
    
    private Integer isEnabled;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    private Integer createBy;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editTime;
    
    private Integer editBy;
    
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    
    @Serial
    private static final long serialVersionUID = 1L;
}
