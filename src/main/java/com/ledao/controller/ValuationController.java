package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.OrderGoods;
import com.ledao.entity.R;
import com.ledao.entity.Valuation;
import com.ledao.service.CustomerService;
import com.ledao.service.GoodsService;
import com.ledao.service.OrderGoodsService;
import com.ledao.service.ValuationService;
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
 * 评价Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-31 19:31
 */
@RestController
@RequestMapping("/valuation")
public class ValuationController {

    @Resource
    private ValuationService valuationService;

    @Resource
    private OrderGoodsService orderGoodsService;

    @Resource
    private CustomerService customerService;

    @Resource
    private GoodsService goodsService;

    /**
     * 不分页条件查询评价
     *
     * @param valuation
     * @return
     */
    @GetMapping("/listNoPage")
    public R listNoPage(Valuation valuation) {
        Map<String, Object> map = new HashMap<>(16);
        QueryWrapper<Valuation> valuationQueryWrapper = new QueryWrapper<>();
        if (valuation.getGoodsId() != null) {
            valuationQueryWrapper.eq("goodsId", valuation.getGoodsId());
        }
        if (valuation.getCustomerId() != null) {
            valuationQueryWrapper.eq("customerId", valuation.getCustomerId());
        }
        valuationQueryWrapper.orderByDesc("addDate");
        List<Valuation> valuationList = valuationService.list(valuationQueryWrapper);
        for (Valuation valuation1 : valuationList) {
            valuation1.setCustomer(customerService.findById(valuation1.getCustomerId()));
            valuation1.setGoods(goodsService.findById(valuation1.getGoodsId()));
        }
        map.put("valuationList", valuationList);
        return R.ok(map);
    }

    /**
     * 添加评价
     *
     * @param valuation
     * @return
     */
    @PostMapping("/add")
    public R add(Valuation valuation) {
        OrderGoods orderGoods = orderGoodsService.findById(valuation.getOrderGoodsId());
        orderGoods.setAppraiseState(1);
        orderGoodsService.update(orderGoods);
        valuation.setGoodsId(orderGoods.getGoodsId());
        valuation.setAddDate(new Date());
        valuationService.add(valuation);
        return R.ok();
    }
}
