package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzp
 * @since 2019-03-10
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;



    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 后台账号
     */
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 手机号
     */
    @TableField("phone")
    private Long phone;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Long createAt;

    /**
     * 最后更改时间
     */
    @TableField("change_at")
    private Long changeAt;

    /**
     * 该用户所拥有的角色
     * 不作为数据库字段使用
     */
    @TableField(exist = false)
    private List<Role> roles;
}
