package com.harvey.anno;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataAuthScope {
    public String tableAlias() default "";
    
    public String tableField() default "";
}
