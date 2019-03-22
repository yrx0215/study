package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    IPage<List<Student>> selectByMultiple(IPage iPage
                                  ,@Param("nickName") String nickName
                                  ,@Param("account") String account
                                  ,@Param("grade") String grade
                                  ,@Param("state") Integer state
                                  ,@Param("starMin") Long starMin
                                  ,@Param("starMax") Long starMax
                                  ,@Param("studyLessonMin") Integer studyLessonMin
                                  ,@Param("studyLessonMax") Integer studyLessonMax);

}
