package com.ledao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 16:35
 */
@Data
@TableName(value = "t_order")
public class Order {

    /**
     * 编号
     */
    @TableId
    private Integer id;

    /**
     * 添加时间
     */
    @TableField(value = "addDate")
    private Date addDate;

    /**
     * 状态,0代表订单已提交,1代表已付款,2代表待付款,3代表已取消,4代表已完成
     */
    private Integer state;

    /**
     * 价格
     */
    private Double price;

    /**
     * 顾客id
     */
    @TableField(value = "customerId")
    private Integer customerId;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货手机
     */
    @TableField(value = "phoneNum")
    private String phoneNum;

    /**
     * 收货人姓名
     */
    @TableField(value = "customerName")
    private String customerName;

    /**
     * 该订单的商品
     */
    @TableField(exist = false)
    private List<OrderGoods> orderGoodsList;
}
