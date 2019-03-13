package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Course;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * course课程相关信息
 * @author yrx
 */
@Component
public interface CourseMapper extends BaseMapper<Course> {


    /**
     * 查询全部课程数据
     * @return 课程列表
     */
     List<Course> selectAllCourse();

    /**
     * 新增课程
     * @param course 新增课程信息
     * @return 返回值为新增课程的id
     */
     Long addCourse(Course course);

    /**
     * 根据id选择相应课程
     * @param id 课程id
     * @return 返回值为课程所有信息
     */
     Course selectCourseById(Long id);

    /**
     * 根据课程id 修改相应信息
     * @param course 修改的课程信息 id必须包含
     * @return 返回值为 true 修改成功 false 失败
     */
     Boolean updateCourse(Course course);


    /**
     * 模糊查询课程列表 并分页
     * @param iPage  分页对象
     * @param subjectName 科目名称
     * @param conurseName  课程名称
     * @param courseStatus 课程状态
     * @return 返回值为查询的对象列表
     */
    IPage selectCourseByFuzzy(IPage iPage,
                              Integer subjectName,
                              Integer courseStatus,
                              String conurseName);

}
