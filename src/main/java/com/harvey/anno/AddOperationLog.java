package com.harvey.anno;

import com.harvey.convert.OperationLogConvert;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddOperationLog {
    String desc() default "";
    
    Class<? extends OperationLogConvert> convert() default OperationLogConvert.class;
}
