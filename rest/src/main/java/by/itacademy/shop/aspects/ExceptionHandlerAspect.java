package by.itacademy.shop.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ExceptionHandlerAspect {

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
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("/errors/json-parse-error");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("/errors/post-file-error");
        }
        catch (Throwable e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ModelAndView("/errors/500");
        }
    }
}
