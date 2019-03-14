package com.jnshu.dreamteam.config.exceptionHandler;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceDaoException.class)
    @ResponseBody
    public Response ServiceDaoExceptionHandler(ServiceDaoException sde){
        return new Response(-1,sde.getMessage());
    }

    @ExceptionHandler(ValidatedParamsOnlyException.class)
    @ResponseBody
    public Response ValidatedParamsOnlyExceptionHandler(ValidatedParamsOnlyException vpoe){
        return new Response(-1,vpoe.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response ExceptionHandler(Exception vpoe){
        return new Response(100,vpoe.getMessage());
    }


}
