package com.jeequan.jeepay.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lst
 * @Date: 2024/2/28
 **/
@Data
@TableName("metaxsire_secret_key")
public class MetaxsireSecretKey {

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 密钥
     */
    @TableField("secret_key")
    private String secretKey;

    /**
     * 逻辑删除字段 1：正常 -1：已删除
     */
    @TableField("deleted_flag")
    @TableLogic
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
