package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Order;

import java.util.List;

/**
 * 订单Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 21:02
 */
public interface OrderService {

    /**
     * 分页条件查询
     *
     * @param orderQueryWrapper
     * @param orderPage
     * @return
     */
    List<Order> list(QueryWrapper<Order> orderQueryWrapper, Page<Order> orderPage);

    /**
     * 获取记录数
     *
     * @param orderQueryWrapper
     * @return
     */
    Long getCount(QueryWrapper<Order> orderQueryWrapper);

    /**
     * 不分页条件查询
     *
     * @param orderQueryWrapper
     * @return
     */
    List<Order> list(QueryWrapper<Order> orderQueryWrapper);

    /**
     * 添加
     *
     * @param order
     * @return
     */
    int add(Order order);

    /**
     * 修改
     *
     * @param order
     * @return
     */
    int update(Order order);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Order findById(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
