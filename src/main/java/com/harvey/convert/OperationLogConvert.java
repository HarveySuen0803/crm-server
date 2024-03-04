package com.harvey.convert;

import com.harvey.domain.OperationLog;

public interface OperationLogConvert<T> {
    OperationLog convert(T t);
}
