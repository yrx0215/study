package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Mission;
import com.jnshu.dreamteam.pojo.MissionContent;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Response selectAllMission(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size,
                                     Long SubjectId,
                                     Long courseId,
                                     Long lessonId){
        log.info("查询所有数据===");
        IPage<Mission> mission = missionService.getAllMission(page,size);
        log.info("mission 的大小是 " + mission.getTotal());
        return new Response(0,"success",mission);

    }


    @RequestMapping(value = "/a/u/missionStatus",method = RequestMethod.PUT)
    public Response updateMissionStatus(Long id){
        log.info("上下架更新===id是{}",id);
        Mission mission = missionService.selectMissionStatus(id);
        Integer missionStatus = mission.getMissionStatus();
        log.info("mission 的当前状态是{}",missionStatus);
        if (missionStatus == 0){
            mission.setMissionStatus(1);
        } else {
            mission.setMissionStatus(0);
        }
        mission.setUpdateAt(System.currentTimeMillis());
        Boolean b = missionService.updateMissionStatus(id);
        if (!b){
            return Response.error();
        }
        return  Response.ok();
    }


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

    // todo 模糊查询

    /**
     * 添加mission信息
     * @param mission 需要添加的信息
     * @return 返回值为新添加的mission的id
     */
    @RequestMapping(value = "/a/u/mission",method = RequestMethod.POST)
    public Response addMission(@RequestBody Mission mission){
        log.info("mission is " + mission);
        String subjectName = subjectService.selectSubject(mission.getSubjectId()).getSubjectName();
        String courseName = courseService.selectCourseById(mission.getCourseId()).getCourseName();
        String lessonName = lessonService.getLessonById(mission.getLessonId()).getLessonName();
        mission.setSubjectName(subjectName);
        mission.setCourseName(courseName);
        mission.setLessonName(lessonName);

        mission.setCreateAt(System.currentTimeMillis());
        missionService.addMission(mission);
        Long missionId = mission.getId();
        log.info("mission id is " + missionId);
        //循环遍历mission中的missionContent数据
        List<MissionContent> missionContents = mission.getMissionContent();
        //判断missionContent中是否有数据
        if (missionContents.isEmpty() ||missionContents.size() == 0 || "".equals(missionContents)  ){
            log.info("missionContents is null  ");
            return Response.error();
        }
        //循环遍历missionContent
        for (int i = 0; i < missionContents.size(); i++) {
            MissionContent missionContent = missionContents.get(i);
            missionContent.setMissionId(missionId);
            missionContent.setCreateAt(System.currentTimeMillis());
            missionContentService.addMissionContent(missionContent);
        }
        return new Response(0,"success",mission.getId());
    }


    @RequestMapping(value = "/a/u/mission/{id}",method = RequestMethod.GET)
    public Response selectMissionById(@PathVariable("id") Long id){
        //todo 需要修改
        log.info("根据id查找数据 id是{}",id);
        Mission mission = missionService.selectMissionById(id);
        log.info("mission数据是:{}",mission);
        return new Response(0,"success", mission);
    }

    @RequestMapping(value = "/a/u/mission/{id}",method = RequestMethod.PUT)
    public Response updateMissionById(Mission mission){
        //todo 更新方法
        MissionContent missionContent = new MissionContent();
        missionContent.setMissionId(1L);
        missionContent.setContentStep(1);
        missionContent.setContentType(1);
        missionContent.setContent("https://www.bilibil.com");

        MissionContent missionContent1 = new MissionContent();
        missionContent1.setMissionId(1L);
        missionContent1.setContentStep(2);
        missionContent1.setContentType(2);
        missionContent1.setContent("https://www.github.com");
        List<MissionContent> list = new ArrayList<MissionContent>();
        list.add(missionContent);
        list.add(missionContent1);

        mission.setMissionName("test mission Name update");
        mission.setMissionContent(list);

        return new Response(0,"success", mission);
    }


}
