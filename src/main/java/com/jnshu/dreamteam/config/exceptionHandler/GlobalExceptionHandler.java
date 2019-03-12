package com.jnshu.dreamteam.config.exceptionHandler;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceDaoException.class)
    @ResponseBody
    public Response ServiceDaoException(ServiceDaoException sde){
        return new Response(100,sde.getMessage());
    }


}
