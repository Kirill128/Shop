package by.itacademy.shop.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@Aspect
@Component
public class LogExceptionHandlerAspect {

    private static Logger logger = Logger.getLogger(LogExceptionHandlerAspect.class);

    @Around("@annotation(by.itacademy.shop.api.annotations.LogExceptionCatchable)")
    public Object logAroundAdvise(ProceedingJoinPoint joinPoint) {
        String errorStr="ERROR WAS MADE !!!!!! MESSAGE: '%s' ";
        try {
           Object o;
           o = joinPoint.proceed();
           logger.info(String.format("Method \"%s\" (%s) was executed ! ",joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs())));
           return o;
        }
        catch(JsonProcessingException e){
            logger.error(String.format(errorStr,e.getMessage()));
            return new ModelAndView("/errors/json-parse-error");
        }
        catch(IOException e){
            logger.error(String.format(errorStr,e.getMessage()));
            return new ModelAndView("/errors/post-file-error");
        }
        catch (Throwable e) {
            logger.error(String.format(errorStr,e.getMessage()));
            return new ModelAndView("/errors/500");
        }
    }
}
