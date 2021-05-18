package by.itacademy.shop.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static Logger logger = Logger.getLogger(LogAspect.class);

    @Pointcut("@annotation(by.itacademy.shop.api.annotations.Loggable)")
    public void makeLog(){}

    @Around("makeLog()")
    public Object logBefore(ProceedingJoinPoint joinPoint){
        try{
            Object o=joinPoint.proceed();
            logger.info("\n\n\nIt WORKS\n\n\n");
            System.out.println("\n\n\nIt WORKS\n\n\n");
            return o;
        }catch (Throwable e){
            return null;
        }
    }
}
