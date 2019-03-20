package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 任务相关属性和实体类
 * @author yrx
 */
@Data
@Component
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务名称
     */
    @TableField("mission_name")
    private String missionName;

    /**
     * 任务状态 0 下架 1上架
     */
    @TableField("mission_status")
    private Integer missionStatus;

    /**
     * 所属科目名称
     */
    @TableField("subject_id")
    private Long subjectId;

    /**
     * 所属课程名称
     */
    @TableField("course_id")
    private Long courseId;

    /**
     * 所属课时名称
     */
    @TableField("lesson_id")
    private Long lessonId;

    /**
     * 所属科目名称
     */
    @TableField("subject_name")
    private String subjectName;

    /**
     * 所属课程名称
     */
    @TableField("course_name")
    private String courseName;

    /**
     * 所属课时名称
     */
    @TableField("lesson_name")
    private String lessonName;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;

    private List<MissionContent> missionContent;

    public Mission() {
    }

    public Mission(Long id, String missionName, List<MissionContent> missionContent) {
        this.id = id;
        this.missionName = missionName;
        this.missionContent = missionContent;
    }
}
