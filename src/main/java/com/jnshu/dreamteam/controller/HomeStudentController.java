package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.annotation.LogInfo;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.*;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class HomeStudentController {

    @Resource
    private StudentService studentService;


    /**
     * 根据token解析ID 更新用户信息
     * @param student
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */

    @PutMapping("/a/u/HomeStudent")
    public Response updateHomeStudent(@Validated @RequestBody Student student, HttpServletRequest httpServletRequest) throws ServiceDaoException {
        String token = httpServletRequest.getHeader("HomeToken");
        if(token!=null && JwtUtil.verify(token)){
            Long userId = JwtUtil.getClaims(token,"studentId").asLong();
            student.setId(userId);
            studentService.updateHomeStudentById(student);
            return Response.ok();
        }
        return new Response(-1,"不存在token或token不正确");
    }

    /**
     * 更新手机号，验证验证码是否正确
     * @param phone
     * @param validatedCode
     * @return
     */
    @PostMapping("/a/u/verify")
    public Response verifyNewPhone(@RequestParam("phone") Long phone
                               ,@RequestParam("validatedCode") Integer validatedCode
                               ,HttpServletRequest httpServletRequest) throws ServiceDaoException{
        if(studentService.selectVerification(phone,validatedCode)){
            String token = httpServletRequest.getHeader("HomeToken");
            Student student = new Student();
            student.setId(JwtUtil.getClaims(token,"studentId").asLong());
            student.setPhone(phone);
            studentService.updateStudentById(student);
            return new Response(200,"验证码正确");
        }
        return new Response(-1,"手机号或验证码错误");
    }

    /**
     * 判断旧手机号验证码是否正确
     * @param phone
     * @param validatedCode
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @PostMapping("/a/u/student/phone")
    public Response verifyOldPhone(@RequestParam("phone") Long phone
                                  ,@RequestParam("validatedCode") Integer validatedCode
                                  ,HttpServletRequest httpServletRequest) throws ServiceDaoException{
        String token = httpServletRequest.getHeader("HomeToken");
        if(JwtUtil.verify(token)){
            throw new ServiceDaoException("Token错误或过期");
        }
        if(studentService.selectVerification(phone,validatedCode)){
            return new Response(200,"验证码正确");
        }
        return new Response(-1,"验证码错误");
    }

    /**
     * 发送手机短信,不需要判断手机号是否在数据库中
     * @param phone
     * @return
     */
    @LogInfo
    @PostMapping("/a/u/student/message")
    public Response studentPhone(@RequestParam("phone") Long phone) throws Exception{
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
     * 依据用户ID查询用户收藏的课程
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/enshrineCourse")
    public Response<IPage<List<Course>>> enshrineCourse(@RequestParam(value = "page",required = false) Integer page
                                  , HttpServletRequest httpServletRequest) throws ServiceDaoException{
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("HomeToken");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"studentId").asLong();
            IPage<List<Course>> myPage = studentService.selectByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response<IPage<List<Course>>>(-1,"不存在token或token不正确");
    }

    /**
     * 根据用户ID查询其收藏的课时列表
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/enshrineLesson")
    public Response<IPage<List<Lesson>>> enshrineLesson(@RequestParam(value = "page",required = false) Integer page
                                  , HttpServletRequest httpServletRequest) throws ServiceDaoException{

        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("HomeToken");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"studentId").asLong();
            IPage<List<Lesson>> myPage = studentService.selectLessonByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response<IPage<List<Lesson>>>(-1,"不存在token或token不正确");
    }

    /**
     * 依据用户ID查询用户购买的资料
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/buyDatum")
    public Response<IPage<List<Lesson>>> buyDatumByStudentId(@RequestParam(value = "page",required = false) Integer page
                                       , HttpServletRequest httpServletRequest) throws ServiceDaoException{
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("HomeToken");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"studentId").asLong();
            IPage<List<Lesson>> myPage = studentService.selectDatumByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response<>(-1,"不存在token或token不正确");
    }

    /**
     * 依据用户ID查询用户购买的课时
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/buyLesson")
    public Response<IPage<List<Lesson>>> buyLessonByStudentId(@RequestParam(value = "page",required = false) Integer page
                                        , HttpServletRequest httpServletRequest) throws ServiceDaoException{
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("HomeToken");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"studentId").asLong();
            IPage<List<Lesson>> myPage = studentService.selectBuyLessonByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response<>(-1,"不存在token或token不正确");
    }

    @PostMapping("/a/u/home/password")
    public Response changePassword(HttpServletRequest httpServletRequest
                                  ,@RequestParam("newPassword") String newPassword
                                  ,@RequestParam("oldPassword") String oldPassword) throws ServiceDaoException{
        if(newPassword.matches("^[0-9a-zA-Z_!@#$%^&*.,]{6,16}$")){
            String token = httpServletRequest.getHeader("HomeToken");
            Long userId = JwtUtil.getClaims(token,"studentId").asLong();
            log.info("通过自己修改密码的用户ID为：{}",userId);
            Student student = studentService.selectStudentById(userId);
            String MD5 = Md5Utils.stringMD5(oldPassword,student.getCreateAt());
            if(MD5.equals(student.getPassword())){
                Student stu = new Student();
                stu.setPassword(Md5Utils.stringMD5(newPassword,student.getCreateAt()));
                stu.setId(userId);
                studentService.updateStudentById(stu);
                return new Response(200,"修改成功");
            }
            return new Response(-1,"密码错误");
        }
        return new Response(-1,"密码格式错误，必须6-16位");
    }

    @GetMapping("/a/u/home/student")
    public Response<Student> getStudent(HttpServletRequest request){
        String token = request.getHeader("HomeToken");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"studentId").asLong();
            Student student = studentService.getStduent(studentId);
            return new Response<>(200,"查询成功",student);
        }
        return new Response<>(-1,"HomeToken错误或不存在");
    }
}
