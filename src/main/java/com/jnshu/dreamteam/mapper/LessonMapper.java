package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Lesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
@Component
public interface LessonMapper extends BaseMapper<Lesson> {

    /**
     * 查询所有lesson信息 并分页
     * @param iPage 分页对象
     * @return 返回ipage分页对象
     */
    IPage selectAllLesson(IPage iPage);

    /**
     * 根据id查询lesson对象
     * @param id lesson对应的id值
     * @return 返回值为id对应的lesson对象
     */
    Lesson selectLessonById(Long id);

    /**
     * 更新lesson信息
     * @param lesson 需要更新的lesson信息
     * @return 返回值为true 更新成功 false 更新失败
     */
    Boolean updateLesson(Lesson lesson);

    /**
     * 添加lesson
     * @param lesson 添加的lesson信息
     * @return 返回值为新增加额lesson的id
     */
    Long addLesson(Lesson lesson);

    /**
<<<<<<< HEAD
     * 根据条件选择lesson数据
     * @param iPage 分页信息
     * @param subjectName 科目名称
     * @param courseLevel 课程难度
     * @param courseName 课程名称
     * @param lessonStatus 课时状态
     * @param lessonName 课时名称
     * @return 返回所选数据列表
     */
    IPage selectLessonFuzzy(IPage iPage,
                            @Param("subjectName")String subjectName,
                            @Param("courseLevel")Integer courseLevel,
                            @Param("courseName")String courseName,
                            @Param("lessonStatus")Integer lessonStatus,
                            @Param("lessonName")String lessonName);


    /**
     * 删除lesson课时数据
     * @param id lesson 的id
     * @return 返回值为true 删除成功
     */
    Boolean deleteLessonById(@Param("id") Long id);

    /**
     * 根据条件查找到对应的lessonid
     * @param subjectId 科目id
     * @param courseId 课程id
     * @param lessonName 课时名称
     * @return 返回值为课时的id
     */
    Long selectIdBySubjectIdAndCourseIdAndLessonName(@Param("subjectId")Long subjectId,
                                                     @Param("courseId")Long courseId,
                                                     @Param("lessonName")String lessonName);
    /**
     * 依据用户ID查询其收藏的课时列表
     * @param iPage
     * @param studentId
     * @return
     */
    IPage<List<Lesson>> selectLessonByStudentId(IPage iPage, @Param("studentId") Long studentId);

    /**
     * 根据用户ID查询其购买的资料
     * @param iPage
     * @param studentId
     * @return
     */
    IPage<List<Lesson>> selectDatumByStudentId(IPage iPage,@Param("studentId") Long studentId);


    /**
     * 根据用户ID查询其购买的课程
     * @param iPage
     * @param studentId
     * @return
     */
    IPage<List<Lesson>> selectBuyLessonByStudentId(IPage iPage,@Param("studentId") Long studentId);

    /**
     * 查询课时名称, 且不重复
     * @param subjectId 所属科目id
     * @param courseId 所属的课程id
     *
     * @return 返回值为课时列表
     */
    List<String> selectLessonName(@Param("subjectId")Long subjectId,
                                  @Param("courseId")Long courseId);


    /**
     * 更新课时的上下架状态
     * @param lesson 所属课时的id
     * @return 返回值true 更新成功
     */
    Boolean updateStatus(Lesson lesson);

    List selectLessonNameByCourseId(@Param("courseId")Long courseId);

    Boolean inserStudentLesson(@Param("studentId")Long studentId,
                       @Param("classId")Long classId);

    Boolean updateStudentLesson(@Param("studentId")Long studentId,
                        @Param("classId")Long classId,
                        @Param("buy")Integer buy,
                        @Param("enshrine")Integer enshrine,
                        @Param("datum")Integer datum,
                        @Param("lessonStatus")Integer lessonStatus);

    List<Object> selectStudentLesson(@Param("studentId") Long studentId,
                        @Param("classId")Long classId);
}
