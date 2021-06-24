package by.senla.daomicroservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    public String catchException(Exception e){
        log.error("Exception: "+e.getClass()+". "+e.getLocalizedMessage());

        return "ERROR, CHECK DAO MICROSERVICE ";
    }
}
