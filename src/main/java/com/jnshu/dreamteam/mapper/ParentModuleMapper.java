package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.ParentModule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangziping
 * @since 2019-03-14
 */
public interface ParentModuleMapper extends BaseMapper<ParentModule> {

    List<ParentModule> selectModuleAll();

    List<ParentModule> selectModuleByRole(@Param("moduleIds") List<Long> moduleIds);

}
