package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 顾客实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-20 22:02
 */
@Data
@TableName(value = "t_customer")
public class Customer {

    /**
     * 编号
     */
    @TableId
    private Integer id;
    /**
     *昵称
     */
    @TableField(value = "nickName")
    private String nickName;
    /**
     *微信用户openid
     */
    private String openid;
    /**
     *头像图片名称
     */
    @TableField(value = "avatarImageName")
    private String avatarImageName;
}
