package com.harvey.aspect;

import com.harvey.anno.DataAuthScope;
import com.harvey.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataAuthScopeAspect {
    @Autowired
    HttpServletRequest request;
    
    @Pointcut(value = "@annotation(com.harvey.anno.DataAuthScope)")
    private void pointcut() {}
 
    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataAuthScope dataAuthScope = signature.getMethod().getDeclaredAnnotation(DataAuthScope.class);
        String tableAlias = dataAuthScope.tableAlias();
        String tableField = dataAuthScope.tableField();
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRoleId() != 1) {
        }
        return joinPoint.proceed();
    }
}
