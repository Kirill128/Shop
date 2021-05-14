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
    public void makeLog(){}

    @Around("makeLog()")
    public Object logAroundAdvise(ProceedingJoinPoint joinPoint) {

//        log.info("\n\n\n\n"+joinPoint.getSignature()+" was executed !!!!! ARGS:"+ Arrays.toString(joinPoint.getArgs())+"\n\n\n\n");
        System.out.println("\n\n"+joinPoint.getSignature()+" was executed !!!!! ARGS:"+ Arrays.toString(joinPoint.getArgs())+"\n\n");
        try {
           Object o;
           o = joinPoint.proceed();
           if(o instanceof ModelAndView){
               ModelAndView returned=(ModelAndView)o;
               System.out.println("RETURN ModelAndView");
               System.out.println("MODEL MAP: "+(returned.getModelMap()));
               System.out.println("VIEW: "+(returned.getViewName()));
           }else{
               System.out.println("UNKNOWN RETURNED VALUE!");
           }
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
//            log.error("ERROR WAS MADE!!!!!!!!!!!!!");
//            log.error(e.getMessage());
        }
    }
}
