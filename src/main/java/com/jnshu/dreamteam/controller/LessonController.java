package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.config.annotation.LogInfo;
import com.jnshu.dreamteam.pojo.Course;
import com.jnshu.dreamteam.pojo.Lesson;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Subject;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.LessonService;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import com.jnshu.dreamteam.utils.UploadPic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

}
