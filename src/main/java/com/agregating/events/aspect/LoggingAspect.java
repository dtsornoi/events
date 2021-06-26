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

  private final Logger logger = Logger.getLogger(getClass().getName());

  @Pointcut("execution(* com.agregating.events.controller.*.*(..))")
  private void forControllerPackage() {}

  @Pointcut("execution(* com.agregating.events.repository.*.*(..))")
  private void forRepositoryPackage() {}

  @Pointcut("execution(* com.agregating.events.service.*.*(..))")
  private void forServicePackage() {}

  @Pointcut("forRepositoryPackage() || forServicePackage() || forControllerPackage()")
  private void forAppFlow() {}

  @Before("forControllerPackage()")
  public void beforeController(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();

    logger.info("@Before: Controller calling method: " + method);
  }

  @AfterReturning("forControllerPackage()")
  public void afterReturningController(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    logger.info("@AfterReturning:Controller from method: " + method);
  }

  @Before("forServicePackage()")
  public void beforeService(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();

    logger.info("@Before: Service calling method: " + method);
  }

  @AfterReturning("forServicePackage()")
  public void afterReturningService(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    logger.info("@AfterReturning: Service from method: " + method);
  }

  @Before("forRepositoryPackage()")
  public void beforeRepository(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();

    logger.info("@Before: Repository calling method: " + method);
  }

  @AfterReturning("forRepositoryPackage()")
  public void afterReturningRepository(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().getName();
    logger.info("@AfterReturning:Repository from method: " + method);
  }
}
