package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Mission;

/**
 * @author yrx
 */
public interface MissionService {

    /**
     * 添加方法
     * @param mission 添加的mission对象
     * @return 返回新添加的mission对象id
     */
    Long addMission(Mission mission);

    /**
     * 获取全部mission对象
     * @param page 当前页数 ,默认1
     * @param size 页面容量 默认10
     * @return 返回分页后的mission集合
     */
    IPage getAllMission(Integer page,Integer size);

    /**
     * 更新mission上下架状态
     * @param id mission的id
     * @return 返回值为true 更新成功
     */
    Boolean updateMissionStatus(Long id);

    /**
     * 删除mission对象
     * @param id mission的id
     * @return 返回值为true 删除成功
     */
    Boolean deleteMissionById(Long id);

    /**
     * 查询mission对象
     * @param id mission的id
     * @return 返回值是mission对象
     */
    Mission selectMissionById(Long id);

    /**
     *  查询missing上下架状态
     * @param id mission 的id
     * @return 返回值为mission对象
     */
    Mission selectMissionStatus(Long id);



}
