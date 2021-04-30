package com.ocean.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeignAspect {
    @Pointcut("execution(* cc.crrc.microservice.test..*.*(..))")
//    @Pointcut("execution(* cc.crrc.business.rest..*.*(..))")
    protected void feignOperation()
    {

    }

    @Before("feignOperation()")
    @Order(1)
    public void logJoinPoint(JoinPoint jp)
    {
        System.out.println("在发生逻辑之前");
    }
}
