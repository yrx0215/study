package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Mission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


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
     * @param mission mission更新信息
     * @return 返回值为true 更新成功
     */
    Boolean updateMissionStatus(Mission mission);

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

    /**
     * 模糊分页按条件查询
     * @param iPage 分页信息
     * @param subjectName 科目名称
     * @param courseLevel 课程等级
     * @param courseName  科目名称
     * @param lessonName 课时名称
     * @param missionName 任务名称
     * @return 返回为选中条件的数据集合
     */
    IPage selectMissionByFuzzy(IPage iPage,
                               @Param("subjectName")String subjectName,
                               @Param("courseLevel")Integer courseLevel,
                               @Param("courseName")String courseName,
                               @Param("lessonName")String lessonName,
                               @Param("missionName")String missionName);

    Boolean updateMissionNameById(Mission mission);


    List<String> selectMissionNameByCourseId(@Param("lessonId")Long lessonId);

}
