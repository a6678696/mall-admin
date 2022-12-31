package com.ledao.service;

import com.ledao.entity.OrderGoods;

import java.util.List;

/**
 * 订单商品Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 21:17
 */
public interface OrderGoodsService {

    /**
     * 添加
     *
     * @param orderGoods
     * @return
     */
    int add(OrderGoods orderGoods);

    /**
     * 根据订单id获取订单商品
     *
     * @param orderId
     * @return
     */
    List<OrderGoods> listByOrderId(Integer orderId);

    /**
     * 修改
     *
     * @param orderGoods
     * @return
     */
    int update(OrderGoods orderGoods);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    OrderGoods findById(Integer id);
}
