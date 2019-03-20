package com.jnshu.dreamteam.service;

import com.jnshu.dreamteam.pojo.MissionContent;

/**
 * @author yrx
 */
public interface MissionContentService {

    /**
     * 添加content信息
     * @param missionContent content对象
     * @return 返回值为新增content的id
     */
    Long  addMissionContent(MissionContent missionContent);

    /**
     * 删除对应的content对象
     * @param id content对应的id
     * @return 返回为true 删除成功
     */
    Boolean delectMissionContentById(Long id);
}
