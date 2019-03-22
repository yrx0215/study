package com.jnshu.dreamteam.controller;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.*;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.JwtUtil;
import com.jnshu.dreamteam.utils.Md5Utils;
import com.jnshu.dreamteam.utils.MyPage;
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
        String token = httpServletRequest.getHeader("Token");
        if(token!=null && JwtUtil.verify(token)){
            Long userId = JwtUtil.getClaims(token,"userId").asLong();
            student.setId(userId);
            studentService.updateHomeStudentById(student);
            return Response.ok();
        }
        return new Response(-1,"不存在token或token不正确");
    }

    /**
     * 修改手机号，验证验证码是否正确
     * @param phone
     * @param validatedCode
     * @return
     */
    @PostMapping("/a/u/verify")
    public Response verifyPhone(@RequestParam("phone") Long phone
                               ,@RequestParam("validatedCode") Integer validatedCode){
        if(studentService.selectVerification(phone,validatedCode)){
            return new Response(200,"验证码正确");
        }
        return new Response(-1,"手机号或验证码错误");
    }

    /**
     * 依据用户ID查询用户收藏的课程
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/enshrineCourse")
    public Response enshrineCourse(@RequestParam(value = "page",required = false) Integer page
                                  ,HttpServletRequest httpServletRequest) throws ServiceDaoException{
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("Token");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"userId").asLong();
            IPage<List<Course>> myPage = studentService.selectByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response(-1,"不存在token或token不正确");
    }

    /**
     * 根据用户ID查询其收藏的课时列表
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/enshrineLesson")
    public Response enshrineLesson(@RequestParam(value = "page",required = false) Integer page
                                  ,HttpServletRequest httpServletRequest) throws ServiceDaoException{

        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("Token");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"userId").asLong();
            IPage<List<Lesson>> myPage = studentService.selectLessonByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response(-1,"不存在token或token不正确");
    }

    /**
     * 依据用户ID查询用户购买的资料
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/buyDatum")
    public Response buyDatumByStudentId(@RequestParam(value = "page",required = false) Integer page
                                       ,HttpServletRequest httpServletRequest) throws ServiceDaoException{
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("Token");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"userId").asLong();
            IPage<List<Lesson>> myPage = studentService.selectDatumByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response(-1,"不存在token或token不正确");
    }

    /**
     * 依据用户ID查询用户购买的课时
     * @param page
     * @param httpServletRequest
     * @return
     * @throws ServiceDaoException
     */
    @GetMapping("/a/u/home/buyLesson")
    public Response buyLessonByStudentId(@RequestParam(value = "page",required = false) Integer page
                                        ,HttpServletRequest httpServletRequest) throws ServiceDaoException{
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        String token = httpServletRequest.getHeader("Token");
        if(token!=null && JwtUtil.verify(token)){
            Long studentId = JwtUtil.getClaims(token,"userId").asLong();
            IPage<List<Lesson>> myPage = studentService.selectBuyLessonByStudentId(iPage,studentId);
            return new Response<>(200,"查询成功",myPage);
        }
        return new Response(-1,"不存在token或token不正确");
    }

    @PostMapping("/a/u/home/password")
    public Response changePassword(HttpServletRequest httpServletRequest
                                  ,@RequestParam("newPassword") String newPassword
                                  ,@RequestParam("oldPassword") String oldPassword) throws ServiceDaoException{
        if(newPassword.matches("^[0-9a-zA-Z_!@#$%^&*.,]{6,16}$")){
            String token = httpServletRequest.getHeader("Token");
            Long userId = JwtUtil.getClaims(token,"userId").asLong();
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
}
