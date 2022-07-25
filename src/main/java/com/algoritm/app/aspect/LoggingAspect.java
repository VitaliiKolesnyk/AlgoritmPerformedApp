package com.algoritm.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.algoritm.app.controller.AlgoritmController.*(..))")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        String targetMethod = joinPoint.getSignature().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result =  joinPoint.proceed();

        stopWatch.stop();

        log.info("Execution of {} method in {} was finished in {} seconds", targetMethod, targetClass, stopWatch.getTotalTimeSeconds());

        return result;
    }

}
