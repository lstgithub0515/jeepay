package com.jeequan.jeepay.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lst
 * @Date: 2024/2/28
 **/
@Data
@TableName("metaxsire_user")
public class MetaxsireUser {

    /**
     * 钱包用户id
     */
    @TableId(value = "user_id")
    private String userId;

    /**
     * 登陆用户名
     */
    @TableField("login_name")
    private String loginName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户类型
     */
    @TableField("user_type")
    private String userType;

    /**
     * 用户状态 0：正常 1：锁定
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
     * 逻辑删除字段 1：正常 -1：已删除
     */
    @TableField("deleted_flag")
    private Integer deletedFlag;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
}
