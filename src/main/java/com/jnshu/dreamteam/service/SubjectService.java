package com.jnshu.dreamteam.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Subject;

import java.util.List;

/**
 * 科目subject接口
 * @author yrx
 */
public interface SubjectService {

    /**
     * 新增科目
     * @param subject 新增的科目
     * @return 返回值为新增的科目id
     */
    Long addSubject(Subject subject);

    /**
     * 根据id查找对应的科目
     * @param id 科目对应的id
     * @return id对应的科目信息
     */
    Subject selectSubject(Long id);

    /**
     * 更新subject对象
     * @param subject 需要更新的subject信息
     * @return 返回值为true 更新成功 false更新失败
     */
    Boolean updateSubject(Subject subject);

    /**
     * 删除科目方法
     * @param id 科目对应的id
     * @return 返回值为true 删除成功 false为失败
     */
    Boolean delectSubject(Long id);

    /**
     * 查找所有科目信息 并分页
     * @param page 所在页数 默认1
     * @param size 一页最多数据量 默认10
     * @return 返回对应的分页对象
     */
    IPage<Subject> selectAllSubject(Integer page, Integer size);

    /**
     * 根据科目名称和科目状态查询课程
     * @param iPage 分页工具
     * @param subjectStatus 科目状态
     * @param subjectName 科目名称
     * @return 返回分页的subject对象
     */
    IPage<Subject> selectSubjectByStutasOrName(IPage iPage,
                                               Integer subjectStatus,
                                               String subjectName);

    /**
     * 更新科目的状态
     * @param subject 科目的id
     * @return 返回值为true 成功 false 失败
     */
    Boolean updateSubjectStatus(Subject subject);

    /**
     * 查询所有科目名称
     * @return 科目名称列表
     */
    List selectAllSubjectName();

    /**
     * 根据科目名称和年级查询对应的id
     * @param subjectName 年级名称
     * @param grade 年级
     * @return 返回值为对应的id
     */
    Long selectIdByNameAndGrade( String subjectName,
                                 Integer grade);
}
