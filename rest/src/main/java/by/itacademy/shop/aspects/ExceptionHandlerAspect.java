package by.itacademy.shop.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Aspect
@Component
public class ExceptionHandlerAspect {

    private static Logger logger = Logger.getLogger(ExceptionHandlerAspect.class);


    @Pointcut("@annotation(by.itacademy.shop.api.annotations.ExceptionCatchable)")
    public void catchExc(){}

    @Around("catchExc()")
    public Object logAroundAdvise(ProceedingJoinPoint joinPoint) {
        try {
           Object o;
           o = joinPoint.proceed();
           return o;
        }
        catch(JsonProcessingException e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("/errors/json-parse-error");
        }
        catch(IOException e){
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("/errors/post-file-error");
        }
        catch (Throwable e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("/errors/500");
        }
    }
}
