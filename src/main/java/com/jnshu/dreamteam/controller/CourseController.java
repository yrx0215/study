package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Course;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Subject;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import com.jnshu.dreamteam.utils.UploadPic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
    public Response<IPage<Course>> getAllCourse(@RequestParam(value = "page",required = false)Integer page,
                                                @RequestParam(value = "size",required = false) Integer size){
        log.info("查询所有课程信息page:{} size{}",page, size );
        page = page == null || page <= 0 ? 1 : page;
        size = size == null || size <= 0 ? 10 : size;
        MyPage myPage = new MyPage(page, size);
        IPage<Course> courses = courseService.selectAllCourse(myPage);
        log.info("所有课程的 size is = " + courses.getTotal());
        return new Response<>(0,"success",courses);
    }


    /**
     * 更新课程course上下架状态
     * @param id 课程id
     * @return  返回值为200 成功
     */
    @RequestMapping(value = "/a/u/courseStatus/{id}",method = RequestMethod.PUT)
    public Response updateCourseStatus(@PathVariable("id") Long id){
        log.info("更新course上下架状态==== id为 {}",id);
        Course course = courseService.selectCourseById(id);
        log.info("course信息为 :{}" ,course);
        Integer courseStatus = course.getCourseStatus();
        log.info("当前上下架状态为{}",courseStatus);
        if (courseStatus == 0){
            course.setCourseStatus(1);
        } else {
            course.setCourseStatus(0);
        }
        course.setUpdateAt(System.currentTimeMillis());
        courseService.updateCourseStatus(course);
        return new Response(200,"success",null);
    }

    /**
     * 删除 课程信息
     * @param id 课程id
     * @return 成功返回true
     */
    @RequestMapping(value = "/a/u/course/{id}",method = RequestMethod.DELETE)
    public Response deleteCourseById(@PathVariable("id") Long id){
        log.info("删除course id是 : {}",id);
        courseService.deleteCourseById(id);
        return new Response(200,"success",null);
    }

    /**
     * 模糊查询
     * @param page 页数默认1
     * @param size 页面容量 默认10
     * @param subjectName 科目名称
     * @param courseName 课程名称
     * @param courseStatus 课程状态 0 下架 1 上架
     * @param courseLevel 课程难度等级
     * @return 符合条件的集合
     */
    @RequestMapping(value = "/a/u/courseFuzzy",method = RequestMethod.GET)
    public Response<IPage<Course>> selectCourseByFuzzy(@RequestParam(value = "page", required = false) Integer page,
                                                       @RequestParam(value = "size", required = false) Integer size,
                                                       @RequestParam(value = "subjectName", required = false) String subjectName,
                                                       @RequestParam(value = "courseName", required = false) String courseName,
                                                       @RequestParam(value = "courseStatus", required = false) Integer courseStatus,
                                                       @RequestParam(value = "courseLevel",required = false) Integer courseLevel){

        log.info("模糊查询,subjectName = {},courseName = {}, courseStatus = {} ",subjectName, courseName, courseStatus);
        page = page == null || page <= 0 ? 1 : page;
        size = size == null || size <= 0 ? 10 : size;
        IPage myPage = new MyPage(page, size);
        IPage<Course> course = courseService.selectCourseByFuzzy(myPage, subjectName,courseStatus,courseName,courseLevel);
        log.info("course 的长度是 = {}",course.getTotal());
        return new Response<>(200,"success",course);
    }

    /**
     *  插入课程方法
     * @param course 插入的课程信息
     * @return 返回值为新插入的课程id
     */
    @RequestMapping(value = "/a/u/course",method = RequestMethod.POST)
    public Response<Long> addCourse(Course course){
        log.info("插入课程 start 课程相关信息为: {}", course);
        Long subjectId = course.getSubjectId();
        //获取subjectName set到course中
        Subject subject = subjectService.selectSubject(subjectId);
        log.info("所关联的subject 项目为: {}", subject);
        if (EmptyUtil.isEmpty(subject)){
            return new Response(-1,"error","错误的subjectId");
        }
        course.setCreateAt(System.currentTimeMillis());
        course.setSubjectName(subject.getSubjectName());
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
        log.info("获取course信息 id是 {}", id);
        Course course = courseService.selectCourseById(id);
        log.info("course is = " + course);
        return new Response<>(0,"success",course);
    }

    /**
     * 根据subjectId 和课程名称 查找到课程对应id
     * @param subjectId 科目id
     * @param courseName 课程名称
     * @return 返回值为对应的id
     */
    @RequestMapping(value = "/a/u/courseId",method = RequestMethod.GET)
    public Response getIdBySubjectIdAndCourseName(Long subjectId, String courseName){
        log.info("根据科目id和课程名称找到对应的id subjectid : {}, courseName : {}" ,subjectId, courseName);
        Long id = courseService.selectIdBySubejctIdAndCourseName(subjectId,courseName);
        if (EmptyUtil.isEmpty(id)){
            return Response.error();
        }
        log.info("course 的id是{}",id);
        return new Response<>(200,"success",id);
    }

    /**
     * 上传课程封面接口
     * @param file
     * @param courseId
     * @return
     * @throws IOException
     */
    @PostMapping("/a/u/course/img")
    public Response<String> uploadCourseImg(@RequestParam("file") MultipartFile file
                                           ,@RequestParam("courseId") Long courseId) throws IOException {
        String pictureId = System.currentTimeMillis()+""+courseId;
        String url = UploadPic.uploadFactory(file,pictureId,"course");
        return new Response<>(200,"上传封面成功","图片地址为："+url);
    }


    /**
     * 根据科目id查询所属不重复的课程名称
     * @param subjectId 科目id
     * @return 返回值为
     */
    @RequestMapping(value = "/a/u/courseName",method = RequestMethod.GET)
    public Response selectCourseName(Long subjectId){
        log.info("查询不重复的课程名称=======, 所属的课程id为{}",subjectId);
        List courses = courseService.selectCourseName(subjectId);
        log.info("courses 集合的长度为: {}",courses.size());
        Map coursesMap = new LinkedHashMap(16);
        for (int i = 0; i < courses.size(); i++) {
             coursesMap.put(i,courses.get(i));
        }
        return new Response(200,"success",coursesMap);

    }

}
