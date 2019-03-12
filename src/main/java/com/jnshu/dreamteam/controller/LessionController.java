package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Lesson;
import com.jnshu.dreamteam.pojo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessionController {

    @RequestMapping(value = "/a/u/allLesson",method = RequestMethod.GET)
    public Response selectAllLesson(){
        Lesson lesson = new Lesson();
        lesson.setLessonName("lessonName  allLesson");
        lesson.setSubjectName("sujbectname allLesson");
        lesson.setCourseName("courseName allLesson");
        lesson.setLessonStatus(1);

        return new Response(0,"success",lesson);

    }

    @RequestMapping(value = "/a/u/lesson",method = RequestMethod.POST)
    public Response addLesson(Lesson lesson,Long subjectId,Long courseId){
        lesson.setLessonName("lesson  addLesson");
        lesson.setLessonIntroduced("lesson addLesson");
        lesson.setRewardStar(5);
        lesson.setUnlockStar(5);
        lesson.setUnlockCost(9.9);

        lesson.setId(1L);
        return new Response(0,"success",lesson.getId());
    }

    @RequestMapping(value = "/a/u/lesson/{id}", method = RequestMethod.GET)
    public Response selectLessonById(Long lessonId){
        Lesson lesson = new Lesson();
        lesson.setLessonName("lesson selectOne");
        lesson.setLessonIntroduced("lesson selectOne");
        lesson.setRewardStar(5);

        return new Response(0,"success",lesson);
    }

    @RequestMapping(value = "/a/u/lesson/{id}",method = RequestMethod.PUT)
    public Response updateLessonById(Long lessonId, Lesson lesson){
        lesson.setLessonName("lesson update");
        lesson.setLessonIntroduced("lesson update");
        lesson.setRewardStar(5);

        return new Response(0,"success",lesson);
    }

}
