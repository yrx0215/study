package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.CourseMapper;
import com.jnshu.dreamteam.pojo.Course;
import com.jnshu.dreamteam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * course实现类
 * @author yrx
 */
@Repository
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public IPage<Course> selectAllCourse(IPage iPage) {
        return courseMapper.selectAllCourse(iPage);
    }

    @Override
    public Long addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public Course selectCourseById(Long id) {
        return courseMapper.selectCourseById(id);
    }

    @Override
    public Boolean updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    @Override
    public IPage selectCourseByFuzzy(IPage iPage,
                                     String subjectName,
                                     Integer courseStatus,
                                     String courseName,
                                     Integer courseLevel) {
        return courseMapper.selectCourseByFuzzy(iPage,subjectName,courseStatus,courseName,courseLevel);
    }

    @Override
    public Boolean updateCourseStatus(Course course) {
        return courseMapper.updateCourseStatus(course);
    }

    @Override
    public Boolean deleteCourseById(Long id) {
        return courseMapper.deleteCourse(id);
    }

    @Override
    public Long selectIdBySubejctIdAndCourseName(Long subejctId, String courseName) {
        return courseMapper.selectIdBySubejctIdAndCourseName(subejctId, courseName);
    }

    @Override
    public List<String> selectCourseName(Long subjectId) {
        return courseMapper.selectCourseName(subjectId);
    }

    @Override
    public List selectCourseIdBySubjectIdAndCourseLevel(Long subjectId, Integer courseLevel) {
        return courseMapper.selectCourseIdBySubjectIdAndCourseLevel(subjectId,courseLevel);
    }

    @Override
    public List<String> selectCourseNameBySubjectId(Long subjectid) {
        return courseMapper.selectCourseNameBySubjectId(subjectid);
    }

    @Override
    public Boolean inserStudentAndCourse(Long studentId, Long courseId) {
        return courseMapper.inserStudentAndCourse(studentId,courseId);
    }

    @Override
    public List<Long> selectStudentAndCourse(Long studentId) {
        return courseMapper.selectStudentAndCourse(studentId);
    }

    @Override
    public Boolean updateStudentAndCourse(Long studentId, Long courseId,Integer status, Integer collection) {
        return courseMapper.updateStudentAndCourse(studentId, courseId, status, collection);
    }

    @Override
    public Integer selectStudentNumber(Long courseId) {
        return courseMapper.selectStudentNumber(courseId);
    }
}
