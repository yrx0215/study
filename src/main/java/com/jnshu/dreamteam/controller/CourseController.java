package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Course;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * course 表现层
 * @author yrx
 */
@RestController
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectService subjectService;

    /**
     * 查询所有课程信息,
     * @return 返回值为课程信息列表
     */
    @RequestMapping(value = "/a/u/allCourse",method = RequestMethod.GET)
    public Response<List<Course>> getAllCourse(){
        log.info("getAllCourse start" );
        List<Course> courses = courseService.selectAllCourse();
        log.info("allcourse size is = " + courses.size());
        return new Response<>(0,"success",courses);
    }

    /**
     *  插入课程方法
     * @param course 插入的课程信息
     * @return 返回值为新插入的课程id
     */
    @RequestMapping(value = "/a/u/course",method = RequestMethod.POST)
    public Response<Long> addCourse(Course course){
        log.info("addCourse start");
        Long subjectId = course.getSubjectId();
        Integer subjectName = subjectService.selectSubject(subjectId).getSubjectName();
        course.setCreateAt(System.currentTimeMillis());
        course.setSubjectName(subjectName);
        courseService.addCourse(course);
        log.info("addCourse id is = " + course.getId());
        return new Response<>(0,"success",course.getId());

    }

    /**
     * 更新课程方法
     * @param course 需要更新的课程信息
     * @return 返回值为 更新后的课程信息
     */
    @RequestMapping(value = "/a/u/course",method = RequestMethod.PUT)
    public Response<Course> updateCourse(Course course){
        log.info("update course , course id is = " + course.getId());
        course.setUpdateAt(System.currentTimeMillis());
        courseService.updateCourse(course);
        log.info("update course is = " + course );
        return new Response<>(0,"success",course);

    }

    /**
     * 根据课程id查询 对应课程的信息
     * @param id 课程id
     * @return 返回值为id对应的课程信息
     */
    @RequestMapping(value = "/a/u/course/{id}",method = RequestMethod.GET)
    public Response<Course> selectCourseByCourseId(@PathVariable("id") Long id){
        log.info("get course by id start");
        Course course = courseService.selectCourseById(id);
        log.info("course is = " + course);
        return new Response<>(0,"success",course);
    }

    

}
