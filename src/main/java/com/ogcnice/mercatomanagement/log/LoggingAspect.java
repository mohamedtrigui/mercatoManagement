package com.ogcnice.mercatomanagement.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("within(com.ogcnice.mercatomanagement.controllers.*) " +
            "|| within(com.ogcnice.mercatomanagement.services.*) " +
            "|| within(com.ogcnice.mercatomanagement.dao.*)")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // Log input parameters
        logger.info("Method: " + joinPoint.getSignature().toShortString() + ", Input: " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();

        // Log output and processing time
        long elapsedTime = System.currentTimeMillis() - startTime;
        logger.info("Method: " + joinPoint.getSignature().toShortString() + ", Output: " + result + ", Processing Time: " + elapsedTime + "ms");

        return result;
    }
}
