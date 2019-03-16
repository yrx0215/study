package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


/**
 * @author yrx
 */
@Component
public interface SubjectMapper extends BaseMapper<Subject> {

    /**
     *  添加方法
     * @param subject subject对象
     * @return 成功返回true 失败返回false
     */
    Boolean addSubject(Subject subject);

    /**
     * 根据id选择科目
     * @param id 科目id
     * @return 返回id对应的科目信息
     */
    Subject selectSubject(Long id);

    /**
     * 更新subject对象
     * @param subject 修改后的subject对象
     * @return 更新成功返回true 失败返回false
     */
    Boolean updateSubject(Subject subject);

    /**
     * 删除方法
     * @param id 删除方法的id
     * @return 成功返回true 失败返回false
     */
    Boolean delectSubject(Long id);

    /**
     * 查询所有科目信息
     * @param iPage ipage对象, 主要作用用于分页, page和size 数据
     * @return 返回值为ipage对象, 包含分页信息
     */
    IPage<Subject> selectAllSubject(IPage iPage);

    /**
     * 模糊查询 根据状态或科目名称查询信息
     * @param iPage 分页信息
     * @param subjectStatus  科目状态
     * @param subjectName 科目名称
     * @return 返回值为分页后的subject集合
     */
    IPage<Subject> selectSubjectStatusOrName(IPage iPage,
                                            @Param("subjectStatus") Integer subjectStatus,
                                            @Param("subjectName") Integer subjectName);


}
