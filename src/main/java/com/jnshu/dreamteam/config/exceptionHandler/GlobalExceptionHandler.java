package com.jnshu.dreamteam.config.exceptionHandler;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.Response;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // success
    @ExceptionHandler(UnauthorizedException.class)
    public Response unauthorizedExceptionHandle(UnauthorizedException e){
        String message =e.getMessage().substring(e.getMessage().indexOf("[")+1,e.getMessage().lastIndexOf("]"));
        return new Response(403,"拒绝访问，您没有"+message+"权限");
    }

    // failed
    @ExceptionHandler(UnauthenticatedException.class)
    public Response authenticationException(UnauthenticatedException e){
        return new Response(401,"用户未登录,请登录");
    }

    @ExceptionHandler(SecurityException.class)
    public Response SecurityExceptionHandler(SecurityException se){
        return new Response(-1,se.getMessage());
    }


}
