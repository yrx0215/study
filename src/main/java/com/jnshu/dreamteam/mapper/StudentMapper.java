package com.jnshu.dreamteam.mapper;

import com.jnshu.dreamteam.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
public interface StudentMapper extends BaseMapper<Student> {

    Student selectByAccountOrPhone(@Param("account") String account);

}
