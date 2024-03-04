package com.harvey.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Data
public class TestDo implements Serializable {
    private Integer id;
    
    private String name;
    
    private Collection<? extends GrantedAuthority> authorities;
    
    @Serial
    private static final long serialVersionUID = 1L;
}
