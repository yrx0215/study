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
     * @param iPage 分页数据
     * @return 返回分页后的mission集合
     */
    IPage getAllMission(IPage iPage);

    /**
     * 更新mission上下架状态
     * @param mission mission的信息
     * @return 返回值为true 更新成功
     */
    Boolean updateMissionStatus(Mission mission);

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


    /**
     * 按条件查询数据
     * @param iPage 分页信息
     * @param subjectName 课时名称
     * @param courseLevel 课程等级
     * @param courseName 课程名称
     * @param lessonName 课时名称
     * @param missionName 任务名称
     * @return 返回值为选中条件的集合
     */
    IPage selectMissionByFuzzy(IPage iPage,
                               String subjectName,
                               Integer courseLevel,
                               String courseName,
                               String lessonName,
                               String missionName);

    Boolean updateMissionNameById(Mission mission);
}
