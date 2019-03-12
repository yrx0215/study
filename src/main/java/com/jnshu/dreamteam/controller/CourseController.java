package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Course;
import com.jnshu.dreamteam.pojo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @RequestMapping(value = "/a/u/allCourse",method = RequestMethod.GET)
    public Response getCourseBySubjectId(Long id){
        Course course = new Course();
        course.setCourseName("测试CourseName 选择所有课程");
        course.setSubjectName("sujbectName 所有课程");
        course.setCourseIntroduction("测试CourseIntroduction 选择所有课程");
        course.setStudyNumber(100);
        course.setCourseStatus(1);
        course.setCourseLevel(2);
        course.setCoursePic("https://yrx-test.oss-cn-beijing.aliyuncs.com/miku/test");
        course.setSubjectId(1L);
        return new Response(0,"success",course);
    }

    @RequestMapping(value = "/a/u/course",method = RequestMethod.POST)
    public Response addCourseBySubjectId(Course course){
        course.setId(1L);
        return new Response(0,"success",course.getId());

    }

    @RequestMapping(value = "/a/u/course/{id}",method = RequestMethod.PUT)
    public Response updateCourseByCourseId(Long id,Course course){
        course.setCourseName("测试CourseName 更新");
        course.setCourseIntroduction("测试CourseIntroduction 更新");
        course.setSubjectName("subjectName 更新");
        course.setStudyNumber(100);
        course.setCourseStatus(1);
        course.setCourseLevel(2);
        course.setCoursePic("https://yrx-test.oss-cn-beijing.aliyuncs.com/miku/test");
        course.setSubjectId(1L);
        return new Response(0,"success",course);

    }

    @RequestMapping(value = "/a/u/course/{id}",method = RequestMethod.GET)
    public Response selectCourseByCourseId(Long id){
        Course course = new Course();
        course.setCourseName("测试CourseName 单选");
        course.setCourseIntroduction("测试CourseIntroduction 单选");
        course.setStudyNumber(100);
        course.setCourseStatus(1);
        course.setCourseLevel(2);
        course.setCoursePic("https://yrx-test.oss-cn-beijing.aliyuncs.com/miku/test");
        course.setSubjectId(1L);
        return new Response(0,"success",course);
    }

    

}
