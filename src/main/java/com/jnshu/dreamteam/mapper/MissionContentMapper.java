package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.MissionContent;
import org.springframework.stereotype.Repository;

/**
 * @author yrx
 */
@Repository
public interface MissionContentMapper {

    /**
     * 添加content信息
     * @param missionContent content对象
     * @return 返回新增的id
     */
    Long addMissionContent(MissionContent missionContent);

    /**
     * 删除相应的content
     * @param id content对应的id
     * @return 返回true 删除成功
     */
    Boolean delectMissionContentById(Long id);

}
