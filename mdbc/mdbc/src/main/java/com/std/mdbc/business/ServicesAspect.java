package com.std.mdbc.business;

import com.std.mdbc.business.abstracts.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServicesAspect {

    @Autowired
    private UserService userService;


    //one of execution string is works
    @Around("execution(* com.std.mdbc.business.abstracts.*.*(..)) || execution(* com.std.mdbc.business.concretes.*.*(..))")
    public Object serviceBeforeAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
        System.out.println(joinpoint.getSignature());
        System.out.println("before executed");
        joinpoint.proceed();
        System.out.println("method executed.");
        return null;
    }
}
