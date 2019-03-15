package com.jnshu.dreamteam.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author wzp
 * @since 2019-03-13
 */
@Data
public class PhoneVerification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @TableId("phone")
    private Long phone;

    /**
     * 验证码
     */
    @TableField("verification_code")
    private String verificationCode;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private Long createAt;


}
