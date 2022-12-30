package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 订单商品实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 20:38
 */
@Data
@TableName(value = "t_order_goods")
public class OrderGoods {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 商品id
     */
    @TableField(value = "goodsId")
    private Integer goodsId;

    /**
     * 商品实体
     */
    @TableField(exist = false)
    private Goods goods;

    /**
     * 订单id
     */
    @TableField(value = "orderId")
    private Integer orderId;
}
