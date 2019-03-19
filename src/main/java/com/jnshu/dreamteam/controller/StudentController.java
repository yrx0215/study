package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.config.annotation.LogInfo;
import com.jnshu.dreamteam.pojo.PhoneVerification;
import com.jnshu.dreamteam.pojo.Response;;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.MessageUtil;
import com.jnshu.dreamteam.utils.UploadPic;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 前台注册模块
 * @author wzp
 */

@Slf4j
@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 发送手机短信,需要判断手机号是否唯一
     * @param phone
     * @return
     */
    @LogInfo
    @PostMapping("/a/student/phone")
    public Response studentPhone(@RequestParam("phone") Long phone) throws Exception{
        studentService.validatedPhoneOnly(phone);
        String code = MessageUtil.randomNum(6);
        PhoneVerification phoneVerification = new PhoneVerification();
        phoneVerification.setPhone(phone);
        phoneVerification.setVerificationCode(code);
        phoneVerification.setCreateAt(System.currentTimeMillis());
        studentService.phoneVerification(phoneVerification);
        MessageUtil.sendMessage(phone.toString(),code);
        return Response.ok();
    }

    /**
     * 验证手机短信，完成注册
     * @param phone
     * @param password
     * @param validatedCode
     * @return
     */
    @LogInfo
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
     * 需要判断昵称用户名和昵称是否注册过
     * @param account
     * @param nickName
     * @param grade
     * @return
     */
    @LogInfo
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
    @LogInfo
    @PostMapping("/a/u/student/img")
    public Response<String> uploadStudentImg(@RequestParam("a") MultipartFile file, HttpServletRequest httpServletRequest)
                                             throws IOException {
        String url = UploadPic.uploadFactory(file,"test","student");
        return new Response<>(200,"上传成功","图片地址："+url);
    }

    @LogInfo
    @PostMapping("/a/student/login")
    public Response login(@RequestParam("account")String account
                         ,@RequestParam("password") String password
                         ,HttpServletResponse httpServletResponse){
        return Response.ok();


    }
}
