package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Lesson;

/**
 * lesson接口
 * @author yrx
 */
public interface LessonService {

    /**
     *  分页查询所有lesson信息
     * @param iPage 分页对象,
     * @return 返回分页后的对象
     */
    IPage getAllLesson(IPage iPage);

    /**
     * 根据id查询lesson对象
     * @param id lesson的id
     * @return 返回值为id对应的lesson对象
     */
    Lesson getLessonById(Long id);

    /**
     *  更新lesson对象
     * @param lesson 更新的lesson数据
     * @return 返回值为true 更新成功 false 失败
     */
    Boolean updateLesson(Lesson lesson);

    /**
     *  添加方法,
     * @param lesson 需要添加的lesson对象
     * @return 添加的lesson新对象的id值
     */
    Long addLesson(Lesson lesson);

}
