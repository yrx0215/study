package com.jnshu.dreamteam.service.impl;

import com.jnshu.dreamteam.mapper.MissionContentMapper;
import com.jnshu.dreamteam.pojo.MissionContent;
import com.jnshu.dreamteam.service.MissionContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionContentServiceImpl implements MissionContentService {

    @Autowired
    MissionContentMapper missionContentMapper;

    @Override
    public Long addMissionContent(MissionContent missionContent) {
        return missionContentMapper.addMissionContent(missionContent);
    }

    @Override
    public Boolean delectMissionContentById(Long id) {
        return missionContentMapper.delectMissionContentById(id);
    }
}
