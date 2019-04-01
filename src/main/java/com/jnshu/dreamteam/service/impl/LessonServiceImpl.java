package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.LessonMapper;
import com.jnshu.dreamteam.pojo.Lesson;
import com.jnshu.dreamteam.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 实现类
 * @author yrx
 */
@Repository
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public IPage getAllLesson(IPage iPage) {
        return lessonMapper.selectAllLesson(iPage);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonMapper.selectLessonById(id);
    }

    @Override
    public Boolean updateLesson(Lesson lesson) {
        return lessonMapper.updateLesson(lesson);
    }

    @Override
    public Long addLesson(Lesson lesson) {
        return lessonMapper.addLesson(lesson);
    }

    @Override
    public IPage selectLessonFuzzy(IPage iPage, String subjectName, Integer courseLevel, String courseName, Integer lessonStatus, String lessonName) {
        return lessonMapper.selectLessonFuzzy(iPage,subjectName,courseLevel,courseName,lessonStatus,lessonName);
    }

    @Override
    public Boolean deleteLessonById(Long id) {
        return lessonMapper.deleteLessonById(id);
    }

    @Override
    public Long selectIdBySubjectIdAndCourseIdAndLessonName(Long subjectId, Long courseId, String lessonName) {
        return lessonMapper.selectIdBySubjectIdAndCourseIdAndLessonName(subjectId, courseId, lessonName);
    }

    @Override
    public List<String> selectLessonName(Long subjectId, Long courseId) {
        return lessonMapper.selectLessonName(subjectId, courseId);
    }

    @Override
    public Boolean updateStatus(Lesson lesson) {
        return lessonMapper.updateStatus(lesson);
    }

    @Override
    public List selectLessonNameByCourseId(Long courseId) {
        return lessonMapper.selectLessonNameByCourseId(courseId);
    }
}
