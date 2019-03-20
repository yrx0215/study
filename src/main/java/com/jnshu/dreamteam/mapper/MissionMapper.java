package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Mission;
import org.springframework.stereotype.Repository;


/**
 * @author yrx
 */
@Repository
public interface MissionMapper extends BaseMapper<Mission> {


    /**
     *  添加mission
     * @param mission mission信息
     * @return 返回行添加的missionid
     */
    Long addMission(Mission mission);

    /**
     * 查询所有任务
     * @param iPage 分页信息
     * @return 返回所有任务信息
     */
    IPage<Mission> selectAllMission(IPage iPage);

    /**
     * 更新mission上下架状态
     * @param id mission对应的id
     * @return 返回值为true 更新成功
     */
    Boolean updateMissionStatus(Long id);

    /**
     * 删除mission
     * @param id mission的id
     * @return 返回值为true 更新成功
     */
    Boolean deleteMissionById(Long id);

    /**
     * 查询mission
     * @param id mission对应数据
     * @return mission对象
     */
    Mission selectMissionById(Long id);

    /**
     * 查询mission状态
     * @param id mission id
     * @return mission对象
     */
    Mission selectMissionStatus(Long id);

}
