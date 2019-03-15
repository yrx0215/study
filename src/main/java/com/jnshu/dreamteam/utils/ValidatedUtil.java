package com.jnshu.dreamteam.utils;

import com.jnshu.dreamteam.pojo.Response;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidatedUtil {


    /**
     * 参数校验，返回格式不对的信息
     * @param bindingResult
     * @return
     */
    public static Response errorInfo(BindingResult bindingResult){
            String message;
            List<ObjectError> list =bindingResult.getAllErrors();
            message=list.get(0).getDefaultMessage();
            return new Response(100,message);
    }


}
