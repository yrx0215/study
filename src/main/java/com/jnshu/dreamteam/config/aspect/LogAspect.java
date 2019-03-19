package com.jnshu.dreamteam.config.aspect;

import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.config.exception.ValidatedParamsOnlyException;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.jnshu.dreamteam.config.annotation.LogInfo)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Response logInfoAround(ProceedingJoinPoint joinPoint) throws Exception{
       String className = joinPoint.getTarget().getClass().getName();
       String simpleClassName = className.substring(className.lastIndexOf(".")+1);
       String methodName = joinPoint.getSignature().getName();
       HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
       String ip = IPUtils.getIpAddr(request);

       Response response;
       Long startTime = System.currentTimeMillis();
       try {
           response = (Response) joinPoint.proceed();
       }catch (ServiceDaoException | ValidatedParamsOnlyException e){
           response = new Response(-1,e.getMessage());
       }catch (Throwable e){
           log.error("发现系统未知异常，异常名称为{},异常信息为{},抛出异常的位置为类{}的{}方法",e.getClass(),e.getMessage(),
                   e.getStackTrace()[0].getClassName(),e.getStackTrace()[0].getMethodName());
           response = new Response(100,"未知异常");
       }
       Double runTime = (System.currentTimeMillis() - startTime)/1000D;
       if(response.getCode()==200){
           log.info("{}的请求{}类的{}方法成功，执行时间为{}s",ip,simpleClassName,methodName,runTime);
       }else {
           log.info("{}的请求{}类的{}方法失败，前端收到的信息：{}，执行时间为{}s",ip,simpleClassName,methodName,response.getMessage(),runTime);
       }
        return response;
    }
}
