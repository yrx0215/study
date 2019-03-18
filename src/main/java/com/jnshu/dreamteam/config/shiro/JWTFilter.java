package com.jnshu.dreamteam.config.shiro;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        //判断请求的请求头是否带上 "Token"
        if(((HttpServletRequest) request).getHeader("Token") !=null){
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request,response);
                return true;
            }catch (AuthenticationException e){
                //token错误
                responseError(response,e.getMessage());
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Token");
        return authorization!=null;
    }


    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response)  {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request,response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }
    private void responseError(ServletResponse response,String message){
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符会变成空格
            message = URLEncoder.encode(message,"UTF-8");
            httpServletResponse.sendRedirect("/unauthenticated?message="+message);
        }catch (UnsupportedEncodingException e){
            log.error("重定向信息编码异常");
        }catch (IOException e){
            log.error("鉴权重定向io异常");
        }
    }
}
