package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.pojo.StudentCheck;
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

    /**
     * 根据学生id查询学生签到状态
     * @param id 学生id
     * @return 返回值为学生的签到信息
     */
    StudentCheck selectStudentCheckById(Long id);

    /**
     * 插入签到信息
     * @param studentCheck 签到信息
     * @return 返回值为新增签到信息的id
     */
    Long insertStudentCheck(StudentCheck studentCheck);

    /**
     * 更新签到信息
     * @param studentCheck 签到信息
     * @return 返回值为true 更新成功
     */
    Boolean updateStudentCheck(StudentCheck studentCheck);

}
