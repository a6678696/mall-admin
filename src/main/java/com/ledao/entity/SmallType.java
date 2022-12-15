package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品小类实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 22:53
 */
@Data
@TableName(value = "t_type_small")
public class SmallType {

    /**
     *编号
     */
    @TableId
    private Integer id;

    /**
     *名称
     */
    private String name;

    /**
     *排列顺序
     */
    @TableField(value = "sortNum")
    private Integer sortNum;

    /**
     *商品大类id
     */
    @TableField(value = "bigTypeId")
    private Integer bigTypeId;

    /**
     *商品大类名称
     */
    @TableField(value = "bigTypeName")
    private String bigTypeName;
}
