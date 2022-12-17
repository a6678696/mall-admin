package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-16 13:19
 */
@Data
@TableName(value = "t_goods")
public class Goods {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 商品小类id
     */
    @TableField(value = "smallTypeId")
    private Integer smallTypeId;
    /**
     *商品大类和小类结合的名称
     */
    @TableField(value = "typeName")
    private String typeName;
    /**
     *商品详情
     */
    private String details;
    /**
     *价格
     */
    private double price;
    /**
     *商品卡片图片名称
     */
    @TableField(value = "cardImageName")
    private String cardImageName;
    /**
     *是否是热卖商品
     */
    @TableField(value = "hotGoods")
    private Boolean hotGoods;
    /**
     *是否是轮播图商品
     */
    @TableField(value = "swiperGoods")
    private Boolean swiperGoods;
    /**
     *首页轮播图图片名称
     */
    @TableField(value = "swiperImageName")
    private String swiperImageName;
    /**
     *商品描述
     */
    private String description;
    /**
     *销量
     */
    @TableField(value = "salesVolume")
    private Integer salesVolume;
    /**
     *库存
     */
    private Integer stock;
}
