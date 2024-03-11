package com.harvey.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ClueVo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String username;
    
    private String fullName;
    
    private String phone;
    
    private String weixin;
    
    private String qq;
    
    private String email;
    
    private Integer age;
    
    private String job;
    
    private String address;
    
    private String description;
    
    @Serial
    private static final long serialVersionUID = 1L;
}
