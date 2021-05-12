package by.itacademy.shop.api.aspetcs;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(by.itacademy.shop.api.annotations.Loggable)")
    public void makeLog(){}

    @Around("makeLog()")
    public void logAroundAdvise(ProceedingJoinPoint joinPoint)  {
        log.info("\n\n\n\n"+joinPoint.getSignature()+" was executed !!!!! ARGS:"+ Arrays.toString(joinPoint.getArgs())+"\n\n\n\n");
        try {
            joinPoint.proceed();
        }
//        catch (Exception e){
//            log.error("ERROR WAS MADE!!!!!!!!!!!!!");
//            log.error(e.getMessage());
//        }
        catch (Throwable e) {
            log.error("ERROR WAS MADE!!!!!!!!!!!!!");
            log.error(e.getMessage());
        }

    }

}
