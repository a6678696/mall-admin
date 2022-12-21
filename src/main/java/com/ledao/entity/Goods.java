package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    @TableId
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
     * 设置成首页轮播图商品时间
     */
    @TableField(value = "setSwiperGoodsDate")
    private Date setSwiperGoodsDate;
    /**
     * 是否是推荐商品
     */
    @TableField(value = "recommendGoods")
    private Boolean recommendGoods;
    /**
     * 推荐时间
     */
    @TableField(value = "recommendDate")
    private Date recommendDate;
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
    /**
     * 商品详情轮播图图片名称拼接的字符串
     */
    @TableField(value = "goodsDetailsSwiperImageStr")
    private String goodsDetailsSwiperImageStr;
    /**
     * 商品详情轮播图图片名称列表
     */
    @TableField(exist = false)
    private List<String> swiperImageNameList;
}
