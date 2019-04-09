package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Course;

import java.util.List;

/**
 * 课程相关接口
 * @author yrx
 */
public interface CourseService {
    /**
     * 查询全部课程数据
     * @return 课程列表
     */
    IPage<Course> selectAllCourse(IPage iPage);

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
     * 模糊查询课程信息
     * @param iPage 分页相关对象
     * @param subjectName 科目名称
     * @param courseStatus 课程状态
     * @param courseName 课程名称
     * @param  courseLevel 课程等级
     * @return 返回值为符合模糊查询的分页数据
     */
    IPage<Course> selectCourseByFuzzy(IPage iPage,
                                     String subjectName,
                                     Integer courseStatus,
                                     String courseName,
                                      Integer courseLevel);

    /**
     * 更新课程上下架状态
     * @param course 课程信息
     * @return 返回值为true 更新成功
     */
    Boolean updateCourseStatus(Course course);

    /**
     * 删除单条课程数据
     * @param id 课程id
     * @return 返回值为true 删除成功
     */
    Boolean deleteCourseById(Long id);

    /**
     * 根据subjectId 和课程名称 查找到课程对应id
     * @param subejctId 科目id
     * @param courseName 课程名称
     * @return 返回值为对应的id
     */
    Long selectIdBySubejctIdAndCourseName(Long subejctId,
                                          String courseName);

    /**
     * 根据id查询不重复课程名称,
     * @param subjectId 所属科目id
     * @return 返回值为 名称列表
     */
    List<String> selectCourseName(Long subjectId);
    /**
     * 根据科目id和课程等级查询课程信息
     * @param subjectId 科目id
     * @param courseLevel 课程等级
     * @return 返回值为课程id和名称
     */
    List<String> selectCourseIdBySubjectIdAndCourseLevel(Long subjectId,
                                                         Integer courseLevel);

    List<String> selectCourseNameBySubjectId(Long subjectId);

    Boolean inserStudentAndCourse(Long studentId,
                                  Long courseId);



    List<Long> selectStudentAndCourse(Long studentId);

    Boolean updateStudentAndCourse(Long studentId,
                                   Long courseId,
                                   Integer status,
                                   Integer collection);

    Integer selectStudentNumber(Long courseId);
}
