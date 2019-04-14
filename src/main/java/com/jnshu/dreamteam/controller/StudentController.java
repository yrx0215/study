package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.annotation.LogInfo;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.*;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private CourseService courseService;

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
            map.put("studentId",studentId);
            String token = JwtUtil.createToken(map);
            httpServletResponse.setHeader("HomeToken",token);
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
        String token = httpServletRequest.getHeader("HomeToken");
        if(JwtUtil.verify(token)){
            Long userId = JwtUtil.getClaims(token,"studentId").asLong();
            Student student = new Student();
            student.setId(userId);
            student.setStudentAccount(account);
            if(nickName.matches("^[\\u4e00-\\u9fa5]{2,6}$")){
                throw new ServiceDaoException("昵称必须为2-6位中文字符");
            }
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
    public Response<String> uploadStudentImg(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest)
                                             throws IOException,ServiceDaoException {
        String token = httpServletRequest.getHeader("HomeToken");
        if(JwtUtil.verify(token)){
            Long userId = JwtUtil.getClaims(token,"studentId").asLong();
            String pictureId = System.currentTimeMillis()+""+userId;
            String url = UploadPic.uploadFactory(file,pictureId,"student");
            Student student = new Student();
            student.setPicture(url);
            student.setId(userId);
            studentService.updateStudentById(student);
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
        httpServletResponse.setHeader("HomeToken",token);

        return new Response(200,"登录成功");
    }

    @LogInfo
    @GetMapping("/a/u/students")
    public Response<IPage<List<Student>>> selectByMultiple(@RequestParam(value = "page",required = false) Integer page
                                  , @RequestParam(value = "size",required = false) Integer size
                                  , @RequestParam(value = "nickName",required = false) String nickName
                                  , @RequestParam(value = "account",required = false) String account
                                  , @RequestParam(value = "grade",required = false) String grade
                                  , @RequestParam(value = "state",required = false) Integer state
                                  , @RequestParam(value = "starMin",required = false) Long starMin
                                  , @RequestParam(value = "starMax",required = false) Long starMax
                                  , @RequestParam(value = "studyLessonMin",required = false) Integer studyLessonMin
                                  , @RequestParam(value = "studyLessonMax",required = false) Integer studyLessonMax) throws ServiceDaoException{

        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        IPage iPage = new MyPage(page,size);
        IPage<List<Student>> myPage = studentService.selectByMultiple(iPage,nickName,account,grade,state,starMin,starMax,studyLessonMin,studyLessonMax);
        return new Response<>(200,"查询成功",myPage);
    }

    /**
     * 根据用户ID查询该学生收藏的课程
     * @param studentId
     * @return
     * @throws ServiceDaoException
     */
    @LogInfo
    @GetMapping("/a/u/enshrineCourse")
    public Response<IPage<List<Course>>> selectEnshrineCourseByStudentId(@RequestParam("studentId") Long studentId
                                                                        ,@RequestParam(value = "page",required = false) Integer page) throws ServiceDaoException {
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        IPage<List<Course>> myPage = studentService.selectByStudentId(iPage,studentId);
        return new Response<>(200,"查询成功",myPage);
    }

    /**
     * 根据用户ID查询该学生的收藏的课时
     * @param studentId
     * @param page
     * @return
     */
    @LogInfo
    @GetMapping("/a/u/enshrineLesson")
    public Response<IPage<List<Lesson>>> selectEnshrineLessonByStudentId(@RequestParam("studentId") Long studentId
                                                    ,@RequestParam(value = "page",required = false) Integer page){
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        IPage<List<Lesson>> myPage = studentService.selectLessonByStudentId(iPage,studentId);
        return new Response<>(200,"查询成功",myPage);
    }

    /**
     * 根据用户ID查询该学生购买的资料
     * @param studentId
     * @param page
     * @return
     */
    @LogInfo
    @GetMapping("/a/u/buyDatum")
    public Response<IPage<List<Lesson>>> selectDatumByStudentId(@RequestParam("studentId") Long studentId
                                          , @RequestParam(value = "page",required = false) Integer page){
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        IPage<List<Lesson>> myPage = studentService.selectDatumByStudentId(iPage,studentId);
        return new Response<>(200,"查询成功",myPage);
    }

    /**
     * 分页查询，依据用户ID查询该用户购买的课程
     * @param studentId
     * @param page
     * @return
     */
    @LogInfo
    @GetMapping("/a/u/buyLesson")
    public Response<IPage<List<Lesson>>> selectBuyLessonByStudentId(@RequestParam("studentId") Long studentId
                                              , @RequestParam(value = "page",required = false) Integer page){
        page = page==null||page<=0?1:page;
        IPage iPage = new MyPage(page,10);
        IPage<List<Lesson>> mypage = studentService.selectBuyLessonByStudentId(iPage,studentId);
        return new Response<>(200,"查询成功",mypage);
    }


    @GetMapping("/a/u/student/{studentId}")
    public Response<Student> selectStudentById(@PathVariable("studentId") Long studentId){
        return new Response<>(200,"查询成功",studentService.selectStudentById(studentId));}

    @RequestMapping(value = "/a/u/student/check",method = RequestMethod.PUT)
    public Response studentCheckIn(HttpServletRequest request) throws ServiceDaoException{
        String token = request.getHeader("Token");
        if (!JwtUtil.verify(token)){
            return new Response(-1,"token 错误",null);
        }
//        Long id = JwtUtil.getClaims(token,"userId").asLong();
        Long id = 26L;
        log.info("学生签到功能, 学生id是{}",id);
        Student student = studentService.selectStudentById(id);
        log.info("student 信息为: {}",student);
        Long star = student.getStar();
        StudentCheck sc = studentService.selectStudentCheckById(id);
        log.info("当前学生签到情况为:{}",sc);
        //表中没有数据
        if (EmptyUtil.isEmpty(sc)){
            log.info("签到表没有数据, 新增用户签到, 学生id是 :{}",id);
            StudentCheck studentCheck = new StudentCheck();
            studentCheck.setStudentId(id);
            studentCheck.setCheckTime(System.currentTimeMillis());
            studentCheck.setCheckDay(1);
            studentService.insertStudentCheck(studentCheck);
            Long scId = studentCheck.getId();
            log.info("签到表新增, 对应id是{}",scId);
            //更新星星数
            student.setId(id);
            student.setStar(star + 3);
            studentService.updateStudentById(student);
            return new Response(200,"success",null);
        }
        Long checkTimeInDb = sc.getCheckTime();
        //表中有数据
        Long diff = (System.currentTimeMillis() - checkTimeInDb) / (24 * 60 * 60 * 1000);
        if (diff == 0L){
            //当天已经签到
            log.info("当天已经签到");
            return new Response(-1,"已经签到",null);
        }
        if (diff == 1L ){
            //连续签到
            sc.setCheckDay(sc.getCheckDay() + 1);
            sc.setCheckTime(System.currentTimeMillis());
            studentService.updateStudentCheck(sc);
            //更新学生信息
            student.setId(id);
            student.setStar(star + 3);
            studentService.updateStudentById(student);
            return Response.ok();
        }
        if (diff > 1L){
            //断签
            sc.setCheckDay(1);
            sc.setCheckTime(System.currentTimeMillis());
            studentService.updateStudentCheck(sc);
            //更新学生信息;
            student.setId(id);
            student.setStar(star + 3);
            studentService.updateStudentById(student);
        }
        return new Response(200,"success",null);
    }

}
