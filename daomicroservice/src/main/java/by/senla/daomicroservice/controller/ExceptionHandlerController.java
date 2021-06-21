package by.senla.daomicroservice.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
@RequestMapping
@RestControllerAdvice
public class ExceptionHandlerController {
    private static Logger logger= Logger.getLogger(ExceptionHandlerController.class);
    @ExceptionHandler(Exception.class)
    public String catchException(Exception e){
        logger.error(e.getMessage());
        return "ERROR, CHECK DAO MICROSERVICE ";
    }
}
