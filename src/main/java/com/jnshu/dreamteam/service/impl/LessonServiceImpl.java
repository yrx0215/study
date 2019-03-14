package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.LessonMapper;
import com.jnshu.dreamteam.pojo.Lesson;
import com.jnshu.dreamteam.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
