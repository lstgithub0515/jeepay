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
@TableName("metaxsire_wallet")
public class MetaxsireWallet{

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
     * token数量
     */
    @TableField("token_count")
    private String tokenCount;

    /**
     * token到期时间
     */
    @TableField("token_expired_time")
    private Date tokenExpiredTime;

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
