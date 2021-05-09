package com.agregating.events.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * AOP class for logging
 *
 * @author Dmitri Tšornõi
 */

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.agregating.events.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.agregating.events.repository.*.*(..))")
    private void forRepositoryPackage(){}

    @Pointcut("execution(* com.agregating.events.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("forRepositoryPackage() || forServicePackage() || forControllerPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();

        logger.info("@Before: calling method: " + method);

        Object[] args = joinPoint.getArgs();

        for (Object argument: args){
            logger.info("argument: " + argument);
        }
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().getName();
        logger.info("@AfterReturning: from method: " + method);

        logger.info("result: " + result);
    }
}
