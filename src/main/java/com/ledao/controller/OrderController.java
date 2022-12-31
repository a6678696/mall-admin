package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Goods;
import com.ledao.entity.Order;
import com.ledao.entity.OrderGoods;
import com.ledao.entity.R;
import com.ledao.service.GoodsService;
import com.ledao.service.OrderGoodsService;
import com.ledao.service.OrderService;
import org.springframework.web.bind.annotation.*;

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
     * 分页查询订单
     *
     * @param order
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(Order order, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Page<Order> orderPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        if (order.getCustomerName() != null) {
            orderQueryWrapper.like("customerName", order.getCustomerName());
        }
        if (order.getPhoneNum() != null) {
            orderQueryWrapper.like("phoneNum", order.getPhoneNum());
        }
        if (order.getState() != null) {
            orderQueryWrapper.eq("state", order.getState());
        }
        orderQueryWrapper.orderByDesc("addDate");
        Map<String, Object> map = new HashMap<>(16);
        List<Order> orderList = orderService.list(orderQueryWrapper, orderPage);
        for (Order order1 : orderList) {
            order1.setOrderGoodsList(orderGoodsService.listByOrderId(order1.getId()));
            for (OrderGoods orderGoods : order1.getOrderGoodsList()) {
                orderGoods.setGoods(goodsService.findById(orderGoods.getGoodsId()));
            }
        }
        Long total = orderService.getCount(orderQueryWrapper);
        map.put("orderList", orderList);
        map.put("total", total);
        return R.ok(map);
    }

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
        //如果是取消订单就要恢复库存
        if (state == 3) {
            List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(orderId);
            for (OrderGoods orderGoods : orderGoodsList) {
                Goods goods = goodsService.findById(orderGoods.getGoodsId());
                goods.setSalesVolume(goods.getSalesVolume() - orderGoods.getNum());
                goods.setStock(goods.getStock() + orderGoods.getNum());
                goodsService.update(goods);
            }
        }
        return R.ok();
    }

    /**
     * 根据id获取订单
     *
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(Integer id) {
        Map<String, Object> map = new HashMap<>(16);
        Order order = orderService.findById(id);
        order.setOrderGoodsList(orderGoodsService.listByOrderId(order.getId()));
        for (OrderGoods orderGoods : order.getOrderGoodsList()) {
            orderGoods.setGoods(goodsService.findById(orderGoods.getGoodsId()));
        }
        map.put("order", order);
        return R.ok(map);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        int key = orderService.deleteById(id);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.ok("删除失败");
        }
    }
}
