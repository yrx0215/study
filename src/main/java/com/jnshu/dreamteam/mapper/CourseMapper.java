package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Course;
import org.apache.ibatis.annotations.Param;
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
     * @param iPage 分页参数
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
     * 模糊查询课程列表 并分页
     * @param iPage  分页对象
     * @param subjectName 科目名称
     * @param courseName  课程名称
     * @param courseStatus 课程状态
     * @param courseLevel 课程等级
     * @return 返回值为查询的对象列表
     */
    IPage selectCourseByFuzzy(IPage iPage,
                              @Param("subjectName") String subjectName,
                              @Param("courseStatus") Integer courseStatus,
                              @Param("courseName") String courseName,
                              @Param("courseLevel") Integer courseLevel);


    /**
     * 更新上下架状态
     * @param course 课程信息
     * @return 返回值为true 更新成功
     */
    Boolean updateCourseStatus(Course course);

    /**
     * 删除course信息
     * @param id course的id
     * @return 返回值为true, 删除成功
     */
    Boolean deleteCourse(Long id);

    /**
     * 根据subjectId 和课程名称 查找到课程对应id
     * @param subejctId 科目id
     * @param courseName 课程名称
     * @return 返回值为对应的id
     */
    Long selectIdBySubejctIdAndCourseName(@Param("subjectId") Long subejctId,
                                          @Param("courseName")String courseName);
    /**
     * 根据用户ID查询其收藏的课程
     * by wzp
     * @param studentId
     * @return
     */
    IPage<List<Course>> selectByStudentId(IPage iPage, @Param("studentId") Long studentId);

    /**
     * 根据id查询不重复课程名称,
     * @param subjectId 所属科目id
     * @return 返回值为 名称列表
     */
    List<String> selectCourseName(@Param("subjectId")Long subjectId);
}
