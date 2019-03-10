package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
@Data
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课时名称
     */
    @TableField("lesson_name")
    private String lessonName;

    /**
     * 课时介绍
     */
    @TableField("lesson_introduced")
    private String lessonIntroduced;

    /**
     * 课时状态 0:下架 1:上架
     */
    @TableField("lesson_status")
    private Integer lessonStatus;

    /**
     * 通过课时奖励星星数
     */
    @TableField("reward_statr")
    private Integer rewardStatr;

    /**
     * 解锁课时需要的星星数 : 免费为0 默认为0
     */
    @TableField("unlock_star")
    private Integer unlockStar;

    /**
     * 解锁课时需要的人民币数  0为免费 默认为0
     */
    @TableField("unlock_cost")
    private Double unlockCost;

    /**
     * 所属课程id
     */
    @TableField("course_id")
    private Long courseId;

    /**
     * 创建课时时间
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 修改课时时间
     */
    @TableField("update_at")
    private Long updateAt;


}
