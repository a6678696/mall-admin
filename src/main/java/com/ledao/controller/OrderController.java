package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Order;
import com.ledao.entity.OrderGoods;
import com.ledao.entity.R;
import com.ledao.service.GoodsService;
import com.ledao.service.OrderGoodsService;
import com.ledao.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-28 21:21
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private GoodsService goodsService;

    /**
     * 不分页查询订单
     *
     * @param order
     * @return
     */
    @GetMapping("/listNoPage")
    public R listNoPage(Order order) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("customerId", order.getCustomerId());
        orderQueryWrapper.eq("state", order.getState());
        orderQueryWrapper.orderByDesc("addDate");
        List<Order> orderList = orderService.list(orderQueryWrapper);
        for (Order order1 : orderList) {
            order1.setOrderGoodsList(orderGoodsService.listByOrderId(order1.getId()));
            for (OrderGoods orderGoods : order1.getOrderGoodsList()) {
                orderGoods.setGoods(goodsService.findById(orderGoods.getGoodsId()));
            }
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("orderList", orderList);
        return R.ok(map);
    }

    /**
     * 添加或修改订单
     *
     * @param order
     * @return
     */
    @PostMapping("/save")
    public R save(Order order) {
        Map<String, Object> map = new HashMap<>(16);
        int key;
        if (order.getId() == null) {
            order.setAddDate(new Date());
            key = orderService.add(order);
            map.put("orderId", order.getId());
        } else {
            key = orderService.update(order);
        }
        if (key > 0) {
            return R.ok(map);
        } else {
            return R.error("保存失败");
        }
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param state
     * @return
     */
    @PostMapping("/changeOrderState")
    public R changeOrderState(Integer orderId, Integer state) {
        Order order = orderService.findById(orderId);
        order.setState(state);
        orderService.update(order);
        return R.ok();
    }
}
