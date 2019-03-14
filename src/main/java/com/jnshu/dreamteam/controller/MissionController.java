package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Mission;
import com.jnshu.dreamteam.pojo.MissionContent;
import com.jnshu.dreamteam.pojo.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yrx
 */
@RestController
public class MissionController {

    @RequestMapping(value = "/a/u/allMission",method = RequestMethod.GET)
    public Response selectAllMission(){
        Mission mission = new Mission();
        mission.setMissionName("misson selectAll");
        mission.setSubjectName("mission selectAll");
        mission.setCourseName("mission selectAll");
        mission.setLessonName("mission selectAll");

        return new Response(0,"success",mission);

    }

    @RequestMapping(value = "/a/u/mission",method = RequestMethod.POST)
    public Response addMission(Long subjectId,
                               Long courseId,
                               Long lessonId,
                               Mission mission){

        mission.setMissionName("add");
        mission.setId(1L);

        return new Response(0,"success",mission.getId());
    }


    @RequestMapping(value = "/a/u/mission/{id}",method = RequestMethod.GET)
    public Response selectMissionById(Long missionId){
        Mission mission = new Mission();
        MissionContent missionContent = new MissionContent();
        missionContent.setMissionId(1L);
        missionContent.setContentStep(1);
        missionContent.setContentType(1);
        missionContent.setContent("https://www.baidu.com");

        MissionContent missionContent1 = new MissionContent();
        missionContent1.setMissionId(1L);
        missionContent1.setContentStep(2);
        missionContent1.setContentType(2);
        missionContent1.setContent("https://www.google.com");
        List<MissionContent> list = new ArrayList<MissionContent>();
        list.add(missionContent);
        list.add(missionContent1);

        mission.setMissionName("test mission Name selectOne");
        mission.setMissionContent(list);

        return new Response(0,"success", mission);

    }

    @RequestMapping(value = "/a/u/mission/{id}",method = RequestMethod.PUT)
    public Response updateMissionById(Long missionId,
                                  Mission mission){

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
