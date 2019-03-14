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
    public List<Course> selectAllCourse() {
        return courseMapper.selectAllCourse();
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
                                            Integer subjectName,
                                            Integer courseStatus,
                                            String courseName) {
        return courseMapper.selectCourseByFuzzy(iPage,subjectName,courseStatus,courseName);
    }
}
