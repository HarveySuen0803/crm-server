package com.harvey.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class PageVo<T> implements Serializable {
    private Long totalSize;
    private Long totalPages;
    private List<T> list;
    
    @Serial
    private static final long serialVersionUID = 1L;
}
