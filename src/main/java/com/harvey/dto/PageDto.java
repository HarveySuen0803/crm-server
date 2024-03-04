package com.harvey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto implements Serializable {
    private Integer pageNo = 1;
    private Integer pageSize = 10;
    
    @Serial
    private static final long serialVersionUID = 1L;
}
