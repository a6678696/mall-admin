package com.ledao.controller;

import com.ledao.entity.Goods;
import com.ledao.entity.OrderGoods;
import com.ledao.entity.R;
import com.ledao.service.GoodsService;
import com.ledao.service.OrderGoodsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单商品Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-30 21:03
 */
@RestController
@RequestMapping("/orderGoods")
public class OrderGoodsController {

    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private GoodsService goodsService;

    /**
     * 添加订单商品
     *
     * @param orderGoods
     * @return
     */
    @PostMapping("/add")
    public R add(OrderGoods orderGoods) {
        int key = orderGoodsService.add(orderGoods);
        Goods goods = goodsService.findById(orderGoods.getGoodsId());
        //减库存
        goods.setStock(goods.getStock() - orderGoods.getNum());
        //加销量
        goods.setSalesVolume(goods.getSalesVolume() + orderGoods.getNum());
        goodsService.update(goods);
        if (key > 0) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }
}
