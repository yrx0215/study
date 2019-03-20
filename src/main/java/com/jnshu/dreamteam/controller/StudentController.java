package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.config.annotation.LogInfo;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.PhoneVerification;
import com.jnshu.dreamteam.pojo.Response;;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.JwtUtil;
import com.jnshu.dreamteam.utils.Md5Utils;
import com.jnshu.dreamteam.utils.MessageUtil;
import com.jnshu.dreamteam.utils.UploadPic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        phoneVerification.setVerificationCode(new Integer(code));
        phoneVerification.setCreateAt(System.currentTimeMillis());
        studentService.phoneVerification(phoneVerification);
        MessageUtil.sendMessage(phone.toString(),code);
        return Response.ok();
    }

    /**
     * 验证手机短信，完成注册，返回token
     * @param phone
     * @param password
     * @param validatedCode
     * @return
     */
    @LogInfo
    @PostMapping("/a/student/register")
    public Response studentRegister(@RequestParam("phone") Long phone
                                   ,@RequestParam("password") String password
                                   ,@RequestParam("validatedCode") Integer validatedCode
                                   ,HttpServletResponse httpServletResponse) throws ServiceDaoException {
        if(studentService.selectVerification(phone,validatedCode)){
            Student student = new Student();
            student.setPhone(phone);
            student.setCreateAt(System.currentTimeMillis());
            student.setPassword(Md5Utils.stringMD5(password,student.getCreateAt()));
            Long studentId = studentService.insertStudent(student);
            Map<String,Object> map = new HashMap<>();
            map.put("userId",studentId);
            String token = JwtUtil.createToken(map);
            httpServletResponse.setHeader("Token",token);
            return new Response(200,"注册成功");
        }else {
            log.info("注册失败，验证码错误");
            return new Response(-1,"验证码错误");
        }
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
                                 ,@RequestParam("grade") String grade
                                 ,HttpServletRequest httpServletRequest) throws ServiceDaoException{
        String token = httpServletRequest.getHeader("Token");
        if(JwtUtil.verify(token)){
            Long userId = JwtUtil.getClaims(token,"userId").asLong();
            Student student = new Student();
            student.setStudentAccount(account);
            student.setNickName(nickName);
            student.setGrade(grade);
            studentService.updateStudentById(student);
            return new Response(200,"注册成功");
        }
        return new Response(-1,"token错误");
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
        String token = httpServletRequest.getHeader("Token");
        if(JwtUtil.verify(token)){
            Long userId = JwtUtil.getClaims(token,"userId").asLong();
            String pictureId = System.currentTimeMillis()+""+userId;
            String url = UploadPic.uploadFactory(file,pictureId,"student");
            return new Response<>(200,"上传成功","图片地址为："+url);
        }
        return new Response<>(-1,"Token错误");
    }

    /**
     * 登录接口
     * @param account
     * @param password
     * @param httpServletResponse
     * @return
     */
    @LogInfo
    @PostMapping("/a/student/login")
    public Response login(@RequestParam("account")String account
                         ,@RequestParam("password") String password
                         ,HttpServletResponse httpServletResponse) throws ServiceDaoException{

        Map map = studentService.selectByAccountOrPhone(account,password);
        String token = JwtUtil.createToken(map);
        httpServletResponse.setHeader("Token",token);
        return new Response(200,"登录成功");
    }
}
