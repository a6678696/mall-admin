package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 商品大类实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 21:33
 */
@Data
@TableName(value = "t_type_big")
public class BigType {

    /**
     * 编号
     */
    @TableId
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 排列顺序
     */
    @TableField(value = "sortNum")
    private Integer sortNum;

    /**
     * 该商品大类下的商品小类列表
     */
    @TableField(exist = false)
    List<SmallType> smallTypeList;

    /**
     * 该商品大类下的商品小类数量
     */
    @TableField(exist = false)
    private Integer smallTypeNum;
}
