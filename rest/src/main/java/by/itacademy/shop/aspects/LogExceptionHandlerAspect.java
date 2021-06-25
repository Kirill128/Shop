package by.itacademy.shop.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogExceptionHandlerAspect {
    @Around("@annotation(by.itacademy.shop.api.annotations.LogExceptionCatchable)")
    public Object logAroundAdvise(ProceedingJoinPoint joinPoint) {
        String errorStr="ERROR '%s' WAS MADE !!!!!! MESSAGE: '%s' ";
        try {
           Object o;
           o = joinPoint.proceed();
           log.info(String.format("Method \"%s\" (%s) was executed ! ",joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs())));
           return o;
        }
        catch(JsonProcessingException e){
            log.error(String.format(errorStr,e.getClass(),e.getMessage()));
            return new ModelAndView("/errors/json-parse-error");
        }
        catch(IOException e){
            log.error(String.format(errorStr,e.getClass(),e.getMessage()));
            return new ModelAndView("/errors/post-file-error");
        }
        catch (Throwable e) {
            log.error(String.format(errorStr,e.getClass(),e.getMessage()));
            return new ModelAndView("/errors/500");
        }
    }
}
