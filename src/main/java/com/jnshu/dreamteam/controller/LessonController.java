package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Lesson;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.LessonService;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/a/u/lesson",method = RequestMethod.POST)
    public Response addLesson(Lesson lesson,Long subjectId,Long courseId){
        log.info("add lesson start , lesson is {}, subjectId is {}, courseId is {}",lesson,subjectId, courseId);
        lesson.setSubjectId(subjectId);
        lesson.setCourseId(courseId);
        String subjectName = subjectService.selectSubject(subjectId).getSubjectName();
        String courseName = courseService.selectCourseById(courseId).getCourseName();
        log.info("subjectName = {}, courseName = {}",subjectName, courseName);
        lesson.setSubjectName(subjectName);
        lesson.setCourseName(courseName);
        lesson.setCreateAt(System.currentTimeMillis());
        lessonService.addLesson(lesson);
        log.info("lesson id is " + lesson.getId());

        return new Response(200,"success",lesson.getId());
    }

    @RequestMapping(value = "/a/u/lesson/{id}", method = RequestMethod.GET)
    public Response getLessonById(@PathVariable("id") Long id){
        log.info("get lesson by id , id is " + id);
        Lesson lesson = lessonService.getLessonById(id);
        log.info("lesson is " + lesson);
        return new Response(0,"success",lesson);
    }

    @RequestMapping(value = "/a/u/lesson",method = RequestMethod.PUT)
    public Response updateLesson(Lesson lesson){
        log.info("update lesson , lesson is " + lesson);
        lesson.setUpdateAt(System.currentTimeMillis());
        Boolean isSuccess = lessonService.updateLesson(lesson);
        if (isSuccess == true){
            return new Response(0,"success",lesson);
        }
        return Response.error();
    }

}
