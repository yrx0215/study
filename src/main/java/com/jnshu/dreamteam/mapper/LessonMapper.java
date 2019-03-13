package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Lesson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
@Component
public interface LessonMapper extends BaseMapper<Lesson> {

    /**
     * 查询所有lesson信息 并分页
     * @param iPage 分页对象
     * @return 返回ipage分页对象
     */
    IPage selectAllLesson(IPage iPage);

    /**
     * 根据id查询lesson对象
     * @param id lesson对应的id值
     * @return 返回值为id对应的lesson对象
     */
    Lesson selectLessonById(Long id);

    /**
     * 更新lesson信息
     * @param lesson 需要更新的lesson信息
     * @return 返回值为true 更新成功 false 更新失败
     */
    Boolean updateLesson(Lesson lesson);

    /**
     * 添加lesson
     * @param lesson 添加的lesson信息
     * @return 返回值为新增加额lesson的id
     */
    Long addLesson(Lesson lesson);


}
