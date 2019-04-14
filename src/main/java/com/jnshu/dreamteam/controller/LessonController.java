package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.annotation.LogInfo;
import com.jnshu.dreamteam.config.exception.ServiceDaoException;
import com.jnshu.dreamteam.pojo.*;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.LessonService;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * lesson表现层
 * @author yrx
 */

@RestController
@Slf4j
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    /**
     * 查询所有课时
     * @param page 所在页数 默认1
     * @param size 页面的容量 默认10
     * @return 返回值为所有数据
     */
    @RequestMapping(value = "/a/u/allLesson",method = RequestMethod.GET)
    public Response getAllLesson(@RequestParam(value = "page",required = false) Integer page,
                                 @RequestParam(value = "size",required = false) Integer size){
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        log.info("get all lesson start page is{}, size is {}",page, size);
        IPage lesson = new MyPage(page, size);
        lessonService.getAllLesson(lesson);
        log.info("lesson size is = " + lesson.getTotal());
        return new Response(200,"success",lesson);

    }


    /**
     * 按照所选条件查询数据, 且支持模糊查询
     * @param page  当前页数 默认1
     * @param size 当前页的条数, 默认10
     * @param subjectName 科目名称
     * @param courseLevel 课程等级
     * @param courseName 课程名称
     * @param lessonStatus 课时状态
     * @param lessonName 课时名称
     * @return
     */
    @RequestMapping(value = "/a/u/lessonFuzzy",method = RequestMethod.GET)
    public Response selectLessonByFuzzy(@RequestParam(value = "page",required = false)Integer page,
                                        @RequestParam(value = "size", required = false)Integer size,
                                        @RequestParam(value = "subjectName", required = false)String subjectName,
                                        @RequestParam(value = "courseLevel", required = false)Integer courseLevel,
                                        @RequestParam(value = "courseName", required = false)String courseName,
                                        @RequestParam(value = "lessonStatus", required = false)Integer lessonStatus,
                                        @RequestParam(value = "lessonName", required = false)String lessonName){

            log.info("选择查询, subjecName{},courselevel {}, courseName {}, lessonStatus {}, lessonName {}",subjectName,courseLevel,courseName, lessonStatus, lessonName);
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        MyPage myPage = new MyPage(page,size);
        lessonService.selectLessonFuzzy(myPage, subjectName,courseLevel, courseName, lessonStatus, lessonName);
        if (myPage.getTotal() == 0){
            return new Response(-1,"没有找到相应数据",null);
        }
        log.info("lesson的总数是 :{}" , myPage.getTotal());
        return new Response(200,"success",myPage);
    }


    /**
     * 删除对应lesson数据
     * @param id lesson对应数据
     * @return 返回值为true 删除成功
     */
    @RequestMapping(value = "/a/u/lesson/{id}", method = RequestMethod.DELETE)
    public Response deleteLessonById(@PathVariable("id") Long id){
        log.info("删除lesson id是{}",id);
        Boolean b =  lessonService.deleteLessonById(id);
        if (!b){
            log.info("删除失败 ,没有相应id");
            return Response.error();
        }
        log.info("删除成功");
        return new Response(200,"success",true);

    }


    /**
     * 增加课时
     * @param lesson 课时对象
     * @param subjectId 所属科目id
     * @param courseId 所属 课程id
     * @return
     */
    @RequestMapping(value = "/a/u/lesson",method = RequestMethod.POST)
    public Response addLesson(Lesson lesson,Long subjectId,Long courseId){
        log.info("add lesson start , lesson is {}, subjectId is {}, courseId is {}",lesson,subjectId, courseId);
        lesson.setSubjectId(subjectId);
        lesson.setCourseId(courseId);
        //根据科目 课程id 写入相应的名字
        Subject subject = subjectService.selectSubject(subjectId);
        Course course = courseService.selectCourseById(courseId);
        //判断id值是否能找到相应的数据
        if (EmptyUtil.isEmpty(subject) || EmptyUtil.isEmpty(course)){
            log.info("传入subjectid或courseId异常, subjectId {}, courseId {}", subjectId, courseId);
            return Response.error();
        }
        //根据课程对象找到相应的科目, 课程名称
        String subjectName = subject.getSubjectName();
        String courseName = course.getCourseName();
        log.info("subjectName = {}, courseName = {}",subjectName, courseName);
        lesson.setSubjectName(subjectName);
        lesson.setCourseName(courseName);
        lesson.setCreateAt(System.currentTimeMillis());
        lessonService.addLesson(lesson);
        log.info("lesson id is " + lesson.getId());

        return new Response(200,"success",lesson.getId());
    }

    /**
     * 根据id查询课时信息
     * @param id 课时id
     * @return 返回课时信息
     */
    @RequestMapping(value = "/a/u/lesson/{id}", method = RequestMethod.GET)
    public Response getLessonById(@PathVariable("id") Long id){
        log.info("get lesson by id , id is " + id);
        Lesson lesson = lessonService.getLessonById(id);
        if (EmptyUtil.isEmpty(lesson)){
            log.info("没有找到对应数据, id是 {}",id);
            return new Response(-1, "没有找到对应的信息",null);
        }
        log.info("lesson is " + lesson);
        return new Response(0,"success",lesson);
    }

    /**
     * 更新课时信息
     * @param lesson 更新的课时信息
     * @return 返回更新后的课时信息
     */
    @RequestMapping(value = "/a/u/lesson",method = RequestMethod.PUT)
    public Response updateLesson(Lesson lesson){
        log.info("update lesson , lesson is " + lesson);
        lesson.setUpdateAt(System.currentTimeMillis());
        Boolean isSuccess = lessonService.updateLesson(lesson);
        if (isSuccess){
            log.info("lesson is {}" , lesson);
            return new Response(0,"success",lesson);
        }
        log.info("更新失败");
        return Response.error();
    }

    /**
     * 根据条件查找到对应的lessonid
     * @param subjectId 科目id
     * @param courseId 课程id
     * @param lessonName 课时名称
     * @return 返回值为课时的id
     */
    @RequestMapping(value = "/a/u/lessonId",method = RequestMethod.GET)
    public Response selectIdBySubjectIdAndCourseIdAndLessonName(Long subjectId, Long courseId, String lessonName){
        log.info("根据信息查找lesson id subjectId : {}, courseId : {}, lessonname : {}",subjectId, courseId, lessonName);
        Long id = lessonService.selectIdBySubjectIdAndCourseIdAndLessonName(subjectId,courseId,lessonName);
        if (EmptyUtil.isEmpty(id)){
            return Response.error();
        }
        log.info("lesson 的id是: {}" ,id);
        return new Response (200, "success",id);

    }

    /**
     * 查询课时名称, 且不重复
     * @param subjectId 科目id
     * @param courseId 课程id
     * @return 返回值为对应的课时名称
     */
    @RequestMapping(value = "/a/u/lessonName",method = RequestMethod.GET)
    public Response selectLessonName(Long subjectId,Long courseId){
        log.info("查询不重复的课时名称, subjectId是 {}, courseid是{}",subjectId, courseId);
        List lessons = lessonService.selectLessonName(subjectId,courseId);
        log.info("课时的名称列表长度为 {}",lessons.size());
        Object[] obj = new Object[lessons.size()];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = lessons.get(i);
        }
        return new Response(200,"success",obj);

    }


    /**
     * 更新上下架状态
     * @param id 对应的lessonid
     * @return 返回值为true 更新成功
     */
    @LogInfo
    @RequestMapping(value = "/a/u/lessonStatus/{id}",method = RequestMethod.PUT)
    public Response updateStatus(@PathVariable("id") Long id){
        log.info("更新上下架状态 对应的lessonId是{}",id);
        Lesson lesson =lessonService.getLessonById(id);
        if (EmptyUtil.isEmpty(lesson)){
            return Response.error();
        }
        Integer status = lesson.getLessonStatus();
        log.info("当前lesson的状态是 {}",status);
        if (status == 0){
            lesson.setLessonStatus(1);
        } else {
            lesson.setLessonStatus(0);
        }
        log.info("更改后的lesson状态是 :{}" ,lesson.getLessonStatus());
        lesson.setUpdateAt(System.currentTimeMillis());

        Boolean b = lessonService.updateStatus(lesson);
        if (!b){
            return Response.error();
        }
        return new Response(200,"success","更新后的状态是:" + lesson.getLessonStatus());
    }

    @RequestMapping(value = "/a/u/lesson/name",method = RequestMethod.POST)
    public Response decisionLessonName(String lessonName, Long courseId){
        log.info("根据courseId查到lessonName 并查重,courseid {}, lessonName {}",courseId, lessonName);
        List lesson = lessonService.selectLessonNameByCourseId(courseId);
        Boolean isLesson = lesson.contains(lessonName);
        if (isLesson){
            return new Response(-1,"已经有对应名称, 请更换名称");
        }
        return new Response(200,"success");
    }


    /**
     * 前台, 更新关系表状态
     * @param studentId
     * @param classId
     * @param buy
     * @param enshrine
     * @param datum
     * @param lessonStatus
     * @return
     */
    @RequestMapping(value = "/a/u/user/studentLesson",method = RequestMethod.POST)
    public Response updateStudentLesson(Long studentId, Long classId,
                                        @RequestParam(value = "buy",required = false)Integer buy,
                                        @RequestParam(value = "enshrine", required = false)Integer enshrine,
                                        @RequestParam(value = "datum",required = false)Integer datum,
                                        @RequestParam(value = "lessonStatus", required = false)Integer lessonStatus){

        log.info("前台课时信息,studentId {}, classId {}, buy {}, enshrine {} , datum {}, lessonStatus {}",studentId,classId,buy,enshrine,datum,lessonStatus);
        List list = lessonService.selectStudentLesson(studentId,classId);
        if (EmptyUtil.isEmpty(list)){
            Boolean success = lessonService.inserStudentLesson(studentId,classId);
            if (!success){
                log.error("插入数据失败");
                return Response.error();
            }
        }
        if (!EmptyUtil.isEmpty(buy) || !EmptyUtil.isEmpty(enshrine) || !EmptyUtil.isEmpty(datum) || !EmptyUtil.isEmpty(lessonStatus)){
            Boolean success = lessonService.updateStudentLesson(studentId, classId, buy, enshrine, datum, lessonStatus);
            if (!success) {
                log.error("更新数据失败");
                return Response.error();
            }
        }
        return new Response(200, "success");
    }


    /**
     * 前台, 解锁课时
     * @param studentId
     * @param lessonId
     * @return
     * @throws ServiceDaoException
     */
    @RequestMapping(value = "/a/u/user/unlockLesson",method = RequestMethod.POST)
    public Response lockCourse(Long studentId, Long lessonId) throws ServiceDaoException {
        log.info("解锁课时, studnetId {} lessonid {}",studentId, lessonId);
        //查询学生的星星数
         Student student = studentService.selectStudentById(studentId);
         log.info("查询的学生信息为: {}", student);
         if (EmptyUtil.isEmpty(student)){
            return new Response(-1,"找不到对应用户信息");
         }
         Integer sStar = Math.toIntExact(student.getStar());

        //查询解锁课程所用的星星
        Lesson lesson = lessonService.getLessonById(lessonId);
        if (EmptyUtil.isEmpty(lesson)){
            return new Response(-1,"找不到对应的课程信息");
        }
        Integer needStar = lesson.getLessonStatus();

        //比较
        if (sStar < needStar){
            return new Response(-1, "学生星星数不足");
        }

        sStar = sStar - needStar;
        student.setStar(Long.valueOf(sStar));

        //插入或更新用户课程表, 购买字段
        studentService.updateStudentById(student);
        Boolean success = lessonService.updateStudentLesson(studentId, lessonId, 1,null,null,null);
        if (!success) {
            //不成功, 数据库中没有这个字段
            lessonService.inserStudentLesson(studentId, lessonId);
            lessonService.updateStudentLesson(studentId, lessonId, 1,null,null,null);
            return new Response(200,"success");
        }
        return new Response(200,"success");
    }

    /**
     * 前台, 完成课时
     * @param studentId
     * @param lessonId
     * @return
     */
    @RequestMapping(value = "/a/u/user/lesson", method = RequestMethod.POST)
    public Response finishLesson(Long studentId, Long lessonId) throws ServiceDaoException {
        log.info("完成课时, studentid {}, lessonId {}", studentId, lessonId);
        //查询课时奖励星星数
        Lesson lesson = lessonService.getLessonById(lessonId);
        Integer rewordStar = lesson.getRewardStar();

        //查询关系表中的课程状态;
//        List list = lessonService.selectStudentLesson(studentId, lessonId);
//      会出现bug 如果关系表中已经有完成课程会在此获取数据
//        log.info("关系表中的数据为 : {}",list);

        //更新用户星星数
        Student student = studentService.selectStudentById(studentId);
        Integer sStar = Math.toIntExact(student.getStar());
        Integer newStar = sStar + rewordStar ;
        student.setStar(Long.valueOf(newStar));
        studentService.updateStudentById(student);
        //更新关系表中课程状态;
        lessonService.updateStudentLesson(studentId,lessonId,null,null,null,2);
        return new Response(200,"success");
    }

    /**
     * 根据学生信息查询对应的课时信息
     * @param studentId
     * @param  lessonId
     * @return
     */
    @RequestMapping(value = "/a/u/user/lesson",method = RequestMethod.GET)
    public Response selectStudentLesson(Long studentId, Long lessonId){
        log.info("根据学生id 查询对应的课时信息 studentId{}, lessonId {}", studentId, lessonId);
        List list = lessonService.selectStudentLesson(studentId, lessonId);
        log.info("查询的对应信息是 {}", list);
        return new Response(200,"success",list);
    }
}
