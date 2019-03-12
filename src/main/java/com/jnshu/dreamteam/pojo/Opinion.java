package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 意见
 *
 * @author draper_hxy
 */
@Data
public class Opinion {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("create_at")
    private Long createAt;

    @TableField("update_at")
    private Long updateAt;

    // 昵称
    @TableField("name")
    private String name;

    @TableField("email")
    private String email;

    // 意见
    @TableField("opinion")
    private String opinion;

}
