package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.MissionMapper;
import com.jnshu.dreamteam.pojo.Mission;
import com.jnshu.dreamteam.service.MissionService;
import com.jnshu.dreamteam.utils.MyPage;
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
    public IPage getAllMission(Integer page, Integer size) {
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        IPage mypage = new MyPage(page,size);
        return missionMapper.selectAllMission(mypage);
    }

    @Override
    public Boolean updateMissionStatus(Long id) {
        return missionMapper.updateMissionStatus(id);
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
}
