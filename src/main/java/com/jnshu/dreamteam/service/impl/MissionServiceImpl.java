package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.MissionMapper;
import com.jnshu.dreamteam.pojo.Mission;
import com.jnshu.dreamteam.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yrx
 */
@Service
public class MissionServiceImpl implements MissionService {

    @Autowired
    MissionMapper missionMapper;

    @Override
    public Long addMission(Mission mission) {
        return missionMapper.addMission(mission);
    }

    @Override
    public IPage getAllMission(IPage iPage) {

        return missionMapper.selectAllMission(iPage);
    }

    @Override
    public Boolean updateMissionStatus(Mission mission) {
        return missionMapper.updateMissionStatus(mission);
    }

    @Override
    public Boolean deleteMissionById(Long id) {
        return missionMapper.deleteMissionById(id);
    }

    @Override
    public Mission selectMissionById(Long id) {
        return missionMapper.selectMissionById(id);
    }

    @Override
    public Mission selectMissionStatus(Long id) {
        return missionMapper.selectMissionStatus(id);
    }

    @Override
    public IPage selectMissionByFuzzy(IPage iPage, String subjectName, Integer courseLevel, String courseName, String lessonName, String missionName) {
        return missionMapper.selectMissionByFuzzy(iPage,subjectName,courseLevel,courseName,lessonName,missionName);
    }

    @Override
    public Boolean updateMissionNameById(Mission mission) {
        return missionMapper.updateMissionNameById(mission);
    }
}
