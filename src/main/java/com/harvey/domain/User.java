package com.harvey.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 */
@TableName(value = "user")
@Data
public class User implements Serializable, UserDetails {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String password;
    
    private Integer roleId;
    
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
    
    @TableField(exist = false)
    private List<String> authNameList;
    
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(authNameList);
    }
    
    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    
    @Override
    @JsonIgnore
    public String getUsername() {
        return username;
    }
    
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return isAccountNoExpired == 1;
    }
    
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return isAccountNoLocked == 1;
    }
    
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return isCredentialsNoExpired == 1;
    }
    
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return isEnabled == 1;
    }
}