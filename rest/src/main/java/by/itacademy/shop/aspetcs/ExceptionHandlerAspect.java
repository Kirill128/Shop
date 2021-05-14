package by.itacademy.shop.aspetcs;

import by.itacademy.shop.api.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ExceptionHandlerAspect {

    @Pointcut("@annotation(by.itacademy.shop.api.annotations.ExceptionCatchable)")
    public void makeLog(){}

    @Around("makeLog()")
    public Object logAroundAdvise(ProceedingJoinPoint joinPoint)   {
        log.info("\n\n\n\n"+joinPoint.getSignature()+" was executed !!!!! ARGS:"+ Arrays.toString(joinPoint.getArgs())+"\n\n\n\n");
        try {
           return joinPoint.proceed();
        }
        catch (Throwable e) {
            log.error("ERROR WAS MADE!!!!!!!!!!!!!");
            log.error(e.getMessage());
        }
        return new ModelAndView("redirect:"+ Constants.MAIN_PAGE);
    }
}
