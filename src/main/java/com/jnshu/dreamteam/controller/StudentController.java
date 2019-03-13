package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Response;;
import com.jnshu.dreamteam.utils.UploadPic;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RestController
public class StudentController {


    /**
     * 发送手机短信
     * @param phone
     * @return
     */
    @PostMapping("/a/student/phone")
    public Response studentPhone(@RequestParam("phone") Long phone){
        return Response.ok();
    }

    /**
     * 验证手机短信，完成注册
     * @param phone
     * @param password
     * @param validatedCode
     * @return
     */
    @PostMapping("/a/student/register")
    public Response studentRegister(@RequestParam("phone") Long phone
                                   ,@RequestParam("password") String password
                                   ,@RequestParam("validatedCode") Long validatedCode
                                   ,HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("token","1234567890");
        return Response.ok();
    }


    /**
     * 手机验证后，添加用户名，昵称，年级等信息，需携带token
     * @param account
     * @param nickName
     * @param grade
     * @return
     */
    @PostMapping("/a/u/student")
    public Response insertStudent(@RequestParam("account") String account
                                 ,@RequestParam("nickName") String nickName
                                 ,@RequestParam("grade") String grade){
        return Response.ok();
    }

    /**
     * 上传头像接口，需携带token
     * @param file
     * @param httpServletRequest
     * @return
     * @throws IOException
     */
    @PostMapping("/a/u/student/img")
    public Response<String> uploadStudentImg(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest)
                                             throws IOException {
        String url = UploadPic.uploadFactory(file,"test","student");
        return new Response<>(200,"上传成功","图片地址："+url);
    }





}
