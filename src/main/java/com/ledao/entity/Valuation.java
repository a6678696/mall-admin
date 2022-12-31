package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 评价实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-31 19:23
 */
@Data
@TableName(value = "t_valuation")
public class Valuation {

    /**
     * 编号
     */
    @TableId
    private Integer id;

    /**
     * 订单商品id
     */
    @TableField(value = "orderGoodsId")
    private Integer orderGoodsId;

    /**
     * 评分
     */
    private Integer rate;

    /**
     * 内容
     */
    private String content;

    /**
     * 评价时间
     */
    @TableField(value = "addDate")
    private Date addDate;

    /**
     * 商品id
     */
    @TableField(value = "goodsId")
    private Integer goodsId;

    /**
     * 商品名称,用于后台搜索
     */
    @TableField(exist = false)
    private String goodsName;

    /**
     * 商品实体
     */
    @TableField(exist = false)
    private Goods goods;

    /**
     * 评价的顾客id
     */
    @TableField(value = "customerId")
    private Integer customerId;

    /**
     * 顾客实体
     */
    @TableField(exist = false)
    private Customer customer;
}
