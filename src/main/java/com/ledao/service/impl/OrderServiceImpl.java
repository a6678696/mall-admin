package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Order;
import com.ledao.mapper.OrderMapper;
import com.ledao.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 21:03
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> list(QueryWrapper<Order> orderQueryWrapper, Page<Order> orderPage) {
        return orderMapper.selectPage(orderPage, orderQueryWrapper).getRecords();
    }

    @Override
    public List<Order> list(QueryWrapper<Order> orderQueryWrapper) {
        return orderMapper.selectList(orderQueryWrapper);
    }

    @Override
    public int add(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateById(order);
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.selectById(id);
    }
}
