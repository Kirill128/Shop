package by.itacademy.shop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(by.itacademy.shop.api.annotations.ExceptionCatchable)")
    public void makeLog(){}

    @Before("makeLog()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("Method '"+joinPoint.getSignature()+"("+joinPoint.getArgs()+")"+"' was executed !!");
    }
}
