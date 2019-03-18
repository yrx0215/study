package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.jnshu.dreamteam.config.annotation.Phone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z_]{6,16}$",message ="账号长度必须为6-16位,且不能以数字开头")
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @Pattern(regexp = "^[0-9a-zA-Z_!@#$%^&*.,]{6,16}$",message ="密码长度必须为6-16位")
    @TableField("password")
    private String password;

    /**
     * 手机号
     */

    @Phone(message = "手机号格式错误")
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
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
}
