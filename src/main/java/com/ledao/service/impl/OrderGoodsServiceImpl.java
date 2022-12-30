package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.OrderGoods;
import com.ledao.mapper.OrderGoodsMapper;
import com.ledao.service.OrderGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单商品Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 21:18
 */
@Service("orderGoodsService")
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public int add(OrderGoods orderGoods) {
        return orderGoodsMapper.insert(orderGoods);
    }

    @Override
    public List<OrderGoods> listByOrderId(Integer orderId) {
        QueryWrapper<OrderGoods> orderGoodsQueryWrapper = new QueryWrapper<>();
        orderGoodsQueryWrapper.eq("orderId", orderId);
        return orderGoodsMapper.selectList(orderGoodsQueryWrapper);
    }
}
