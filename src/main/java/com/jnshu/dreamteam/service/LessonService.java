package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Lesson;

import java.util.List;

/**
 * lesson接口
 * @author yrx
 */
public interface LessonService {

    /**
     *  分页查询所有lesson信息
     * @param iPage 分页对象,
     * @return 返回分页后的对象
     */
    IPage getAllLesson(IPage iPage);

    /**
     * 根据id查询lesson对象
     * @param id lesson的id
     * @return 返回值为id对应的lesson对象
     */
    Lesson getLessonById(Long id);

    /**
     *  更新lesson对象
     * @param lesson 更新的lesson数据
     * @return 返回值为true 更新成功 false 失败
     */
    Boolean updateLesson(Lesson lesson);

    /**
     *  添加方法,
     * @param lesson 需要添加的lesson对象
     * @return 添加的lesson新对象的id值
     */
    Long addLesson(Lesson lesson);

    /**
     * 条件查询数据
     * @param iPage 分页信息
     * @param subjectName 科目名称
     * @param courseLevel 所属课程难度
     * @param courseName 课程名称
     * @param lessonStatus 课时状态
     * @param lessonName 课时名称
     * @return 返回选定数据列表
     */
    IPage selectLessonFuzzy(IPage iPage,
                            String subjectName,
                            Integer courseLevel,
                            String courseName,
                            Integer lessonStatus,
                            String lessonName);


    /**
     * 根据id 删除相应数据
     * @param id lesson对应的id
     * @return 返回值为true 删除成功
     */
    Boolean deleteLessonById(Long id);

    /**
     * 根据条件查找到对应的lessonid
     * @param subjectId 科目id
     * @param courseId 课程id
     * @param lessonName 课时名称
     * @return 返回值为课时的id
     */
    Long selectIdBySubjectIdAndCourseIdAndLessonName(Long subjectId,
                                                     Long courseId,
                                                     String lessonName);

    /**
     * 查询课时名称, 且不重复
     * @param subjectId 所属科目id
     * @param courseId 所属的课程id
     *
     * @return 返回值为课时列表
     */
    List<String> selectLessonName(Long subjectId,
                                  Long courseId);

    /**
     * 更新课时的上下架状态
     * @param lesson 所属课时的id
     * @return 返回值true 更新成功
     */
    Boolean updateStatus(Lesson lesson);

}
