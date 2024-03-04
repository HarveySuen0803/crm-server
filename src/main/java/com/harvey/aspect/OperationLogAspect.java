package com.harvey.aspect;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.harvey.anno.AddOperationLog;
import com.harvey.convert.OperationLogConvert;
import com.harvey.domain.OperationLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class OperationLogAspect {
    @Pointcut("@annotation(com.harvey.anno.AddOperationLog)")
    public void operationLogPointCut() {}
    
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
    
    @Around("operationLogPointCut()")
    public Object operationLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            try {
                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                AddOperationLog annotation = methodSignature.getMethod().getAnnotation(AddOperationLog.class);
                
                Class<? extends OperationLogConvert> convert = annotation.convert();
                OperationLogConvert operationLogConvert = convert.getDeclaredConstructor().newInstance();
                OperationLog operationLog = operationLogConvert.convert(joinPoint.getArgs()[0]);
                operationLog.setDescription(annotation.desc());
                operationLog.setResult(JSONUtil.toJsonStr(result));
                
                Db.save(operationLog);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
