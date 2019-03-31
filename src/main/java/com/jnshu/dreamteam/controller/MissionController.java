package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.*;
import com.jnshu.dreamteam.service.*;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author yrx
 */

@RestController
@Slf4j
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private MissionContentService missionContentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonService lessonService;

    /**
     * 查询所有数据
     * @param page 当前页数信息 默认1
     * @param size 页面的容量 默认10
     * @return 返回值为所有的mission信息
     */
    @RequestMapping(value = "/a/u/allMission",method = RequestMethod.GET)
    public Response<IPage> selectAllMission(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size){
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        log.info("查询所有数据===page: {}, size : {}",page, size);

        MyPage myPage = new MyPage(page,size);
        IPage iPage = missionService.getAllMission(myPage);
        if (iPage.getTotal() == 0){
            log.info("没有查询到相应数据");
            return new Response<>(-1, "没有查询到相应数据",null);
        }
        log.info("mission 的大小是 " + iPage.getTotal());
        return new Response<>(0,"success",iPage);

    }


    /**
     * 更新mission的上下架状态
     * @param id mission对应的id
     * @return 返回值为true 更新成功
     */
    @RequestMapping(value = "/a/u/missionStatus/{id}",method = RequestMethod.PUT)
    public Response updateMissionStatus(@PathVariable("id") Long id){
        log.info("上下架更新===id是{}",id);
        Mission mission = missionService.selectMissionStatus(id);
        if (EmptyUtil.isEmpty(mission)){
            log.info("没有找到相依的数据 ,id {}", id);
            return new Response(-1, "没有找到相关的数据",null);
        }
        Integer missionStatus = mission.getMissionStatus();
        log.info("mission 的当前状态是{}",missionStatus);
        if (missionStatus == 0){
            mission.setMissionStatus(1);
        } else {
            mission.setMissionStatus(0);
        }
        mission.setUpdateAt(System.currentTimeMillis());
        Boolean b = missionService.updateMissionStatus(mission);
        if (!b){
            return Response.error();
        }
        return  Response.ok();
    }


    /**
     * 删除对应的mission信息(同时删除missionContent中mission关联的信息)
     * @param id 对应的mission 的id
     * @return 返回值为true 删除成功
     */
    @RequestMapping(value = "/a/u/mission/{id}",method = RequestMethod.DELETE)
    public Response deleteMissionById(@PathVariable("id")Long id){
        log.info("删除mission id是 {}" , id);
        Boolean b = missionService.deleteMissionById(id);
        //删除missionContent中missionId为id的数据;
        Boolean b1 = missionContentService.delectMissionContentById(id);
        if (!b || !b1){
            log.info("删除失败");
            return Response.error();
        }
        return Response.ok();
    }



    @RequestMapping(value = "/a/u/missionFuzzy",method = RequestMethod.GET)
    public Response selectLessonByFuzzy(@RequestParam(value = "page",required = false)Integer page,
                                        @RequestParam(value = "size", required = false)Integer size,
                                        @RequestParam(value = "subjectName", required = false)String subjectName,
                                        @RequestParam(value = "courseLevel", required = false)Integer courseLevel,
                                        @RequestParam(value = "courseName", required = false)String courseName,
                                        @RequestParam(value = "lessonName", required = false)String lessonName,
                                        @RequestParam(value = "missionName", required = false)String missionName){

        log.info("选择查询, subjecName{},courselevel {}, courseName {},, lessonName {}, missionName",subjectName,courseLevel,courseName, lessonName,missionName);
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        MyPage myPage = new MyPage(page,size);
        missionService.selectMissionByFuzzy(myPage,subjectName,courseLevel,courseName, lessonName,missionName);
        if (myPage.getTotal() == 0){
            return new Response(-1,"没有找到相应数据",null);
        }
        log.info("lesson的总数是 :{}" , myPage.getTotal());
        return new Response(200,"success",myPage);
    }

    /**
     * 添加mission信息
     * @param mission 需要添加的信息
     * @return 返回值为新添加的mission的id
     */
    @RequestMapping(value = "/a/u/mission",method = RequestMethod.POST)
    public Response addMission(Mission mission){
        log.info("mission is " + mission);
        //通过subjectId 等三个id 添加相应的名称

        Subject subject = subjectService.selectSubject(mission.getSubjectId());
        Course course = courseService.selectCourseById(mission.getCourseId());
        Lesson lesson = lessonService.getLessonById(mission.getLessonId());
        if (EmptyUtil.isEmpty(subject) || EmptyUtil.isEmpty(course) || EmptyUtil.isEmpty(lesson)){
            log.info("查找数据失败 subject : {}, course : {}, lesson : {}",subject, course, lesson);
            return new Response(-1,"传入信息有误",null);
        }
        //获取对应的名称
        String subjectName = subject.getSubjectName();
        String courseName = course.getCourseName();
        String lessonName = lesson.getLessonName();
        //将名称set到mission中;
        mission.setSubjectName(subjectName);
        mission.setCourseName(courseName);
        mission.setLessonName(lessonName);
        //创建时间
        mission.setCreateAt(System.currentTimeMillis());
        missionService.addMission(mission);
        Long missionId = mission.getId();
        log.info("mission id is " + missionId);
        //循环遍历mission中的missionContent数据
        List<MissionContent> missionContents = mission.getMissionContent();
        //判断missionContent中是否有数据
        if (EmptyUtil.isEmpty(missionContents)){
            log.info("missionContents is null  ");
            return Response.error();
        }
        //循环遍历missionContent
        for (MissionContent missionContent : missionContents) {
            missionContent.setMissionId(missionId);
            missionContentService.addMissionContent(missionContent);
        }
        return new Response<>(0,"success",mission.getId());
    }


    @RequestMapping(value = "/a/u/mission/{id}",method = RequestMethod.GET)
    public Response<Mission> selectMissionById(@PathVariable("id") Long id){
        log.info("根据id查找数据 id是{}",id);
        Mission mission = missionService.selectMissionById(id);
        if (EmptyUtil.isEmpty(mission)){
            return new Response<>(-1,"查询数据有误",null);
        }
        log.info("mission数据是:{}",mission);
        return new Response<>(0,"success", mission);
    }



    /**
     * 更新mission方法
     * @param mission 需要更新的mission信息
     * @return 返回更新后的mission信息
     */
    @RequestMapping(value = "/a/u/mission",method = RequestMethod.PUT)
    public Response<Mission> updateMissionById(Mission mission){
        log.info("更新mission方法 mission = {}",mission);
        Long missionId = mission.getId();
        //删除missionContent中相关id的信息
        Boolean contentSuccess = missionContentService.delectMissionContentById(missionId);
        if (!contentSuccess){
            return new Response(-1,"更新失败, 删除原数据失败","任务对应id没有任务步骤");
        }
        //更新时间
        mission.setUpdateAt(System.currentTimeMillis());
        missionService.updateMissionNameById(mission);
        //新增missionContent中的信息
        List<MissionContent> missionContent= mission.getMissionContent();
        for (MissionContent content : missionContent) {
            log.info("missionContent is {}" , content);
            content.setMissionId(missionId);
            missionContentService.addMissionContent(content);
        }
        return new Response<>(0,"success", mission);
    }

}
