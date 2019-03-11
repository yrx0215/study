package com.jnshu.dreamteam.pojo;

import lombok.Data;
<<<<<<< HEAD
import org.apache.ibatis.jdbc.Null;
=======
>>>>>>> 1fe4c2e59acf425eddce88e24eafce7d906c2794
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class Response<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    private static final long serialVersionUid = 1L;

    public Response() {
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
<<<<<<< HEAD

    public Response(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Response(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }

    public static Response ok(){
        return new Response(StatusCode.SUCCESS);
    }

    public static Response error(){
        return new Response(StatusCode.FAILURE);
    }
=======
>>>>>>> 1fe4c2e59acf425eddce88e24eafce7d906c2794
}
