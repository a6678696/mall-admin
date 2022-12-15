package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 公告实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 2:24
 */
@Data
@TableName(value = "t_announcement")
public class Announcement {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 添加时间
     */
    @TableField(value = "addDate")
    private Date addDate;
}
