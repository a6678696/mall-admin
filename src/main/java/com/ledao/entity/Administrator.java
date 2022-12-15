package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-05 20:24
 */
@Data
@TableName(value = "t_administrator")
public class Administrator {

    /**
     * 编号
     */
    @TableId
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * 密码
     */
    private String password;
}
