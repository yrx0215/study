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
        return new Response(200,"success","变更的状态为:"+ course.getCourseStatus());
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
     * @return
     * @throws IOException
     */
    @PostMapping("/a/u/course/img")
    public Response<String> uploadCourseImg(@RequestParam("file") MultipartFile file) throws IOException {
        String pictureId = System.currentTimeMillis()+"";
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
        Object[] obj = new Object[courses.size()];
        for (int i = 0; i < obj.length; i++) {
            obj[i] = courses.get(i);
        }
        return new Response(200,"success",obj);

    }


    @RequestMapping(value = "/a/u/coursesName",method = RequestMethod.GET)
    public Response selectCourseBySubjectIdAndLevel (Long subjectId,
                                                     Integer courseLevel){
        log.info("根据科目id和课程等级查询课程的名称和id, subjectId {}, courseLevel {}",subjectId, courseLevel);
        List courses = courseService.selectCourseIdBySubjectIdAndCourseLevel(subjectId,courseLevel);
        log.info("查询的课程大小为 : {}",courses.size());
        if (courses.size() == 0){
            return new Response(200,"success","没有找到相应数据");
        }
        return new Response(200, "success",courses);
    }



    @RequestMapping(value = "/a/u/course/name",method = RequestMethod.POST)
    public Response decisionLessonName(String courseName, Long subjectId){
        log.info("根据subjectId查到coursename 并查重,subjectid {}, courseName {}",subjectId, courseName);
        List course = courseService.selectCourseNameBySubjectId(subjectId);
        Boolean isCourse = course.contains(courseName);
        if (isCourse){
            return new Response(-1,"已经有对应名称, 请更换名称");
        }
        return new Response(200,"success");
    }


//    /**
//     * 前台, 新增用户和课程之间的关系, (在学习课程 和开始学习是使用, 增加到关系表中)
//     * @param studentId
//     * @param courseId
//     * @return
//     */
//    @RequestMapping(value = "/a/u/user/studentCourse",method = RequestMethod.POST)
//    public Response addCourseUser(Long studentId, Long courseId){
//
//        log.info("开始课程学习, 增加关系表中数据 学生id为 {} , 课程id为{}",studentId, courseId);
//        Boolean success = courseService.inserStudentAndCourse(studentId,courseId);
//        if (!success){
//            return Response.error();
//        }
//        return Response.ok();
//    }


    /**
     * 前台 , 学生课程表维护
     * @param status
     * @param collection
     * @param studentId
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/a/u/user/studentCourse",method = RequestMethod.POST)
    public Response updateStudentAndCourse(@RequestParam(value = "status", required = false) Integer status,
                                           @RequestParam(value = "collection",required = false) Integer collection,
                                           Long studentId,Long courseId){
        log.info("开始学习, 收藏或者更改状态,status {}, collection {} studentId {} courseId {}",status, collection, studentId, courseId);
        List<Long> list = courseService.selectStudentAndCourse(studentId);
        if (list.contains(courseId)){
            Boolean success = courseService.inserStudentAndCourse(studentId,courseId);
            if (!success){
                log.error("添加数据失败");
                return Response.error();
            }
        }
        //更新学习人数
        Integer studyNumber = courseService.selectCourseById(courseId).getStudyNumber();
        Integer studentNumber = courseService.selectStudentNumber(courseId);
        log.info("实体表中 学习人数 {}, 关系表中人数 {}", studyNumber, studentNumber);
        if (studyNumber > studentNumber){
            log.error("关系表中数据小于实体表中");
             return Response.error();
        }
        Course course = new Course();
        course.setStudyNumber(studentNumber);
        course.setId(courseId);
        course.setUpdateAt(System.currentTimeMillis());
        courseService.updateCourse(course);
        //判断是否为空, 进行更新数据
        if (!EmptyUtil.isEmpty(status) || !EmptyUtil.isEmpty(collection)){
            Boolean success = courseService.updateStudentAndCourse(studentId,courseId,status,collection);
            if (!success){
                log.error("更新status 或 collection失败 ");
                return Response.error();
            }
        }
        return new Response(200, "success");

    }

}
