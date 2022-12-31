package com.ledao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Goods;
import com.ledao.entity.OrderGoods;
import com.ledao.entity.R;
import com.ledao.entity.Valuation;
import com.ledao.service.CustomerService;
import com.ledao.service.GoodsService;
import com.ledao.service.OrderGoodsService;
import com.ledao.service.ValuationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
     * 分页条件查询评价
     *
     * @param valuation
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public R list(Valuation valuation, @RequestParam(value = "currentPage", required = false) Integer currentPage, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Map<String, Object> map = new HashMap<>(16);
        Page<Valuation> valuationPage = new Page<>(currentPage, pageSize);
        QueryWrapper<Valuation> valuationQueryWrapper = new QueryWrapper<>();
        if (valuation.getGoodsName() != null) {
            List<Integer> goodsIdList = new ArrayList<>();
            QueryWrapper<Goods> goodsQueryWrapper = new QueryWrapper<>();
            goodsQueryWrapper.like("name", valuation.getGoodsName());
            List<Goods> goodsList = goodsService.list(goodsQueryWrapper);
            if (goodsList.size() > 0) {
                for (Goods goods : goodsList) {
                    goodsIdList.add(goods.getId());
                }
            } else {
                goodsIdList.add(-1);
            }
            valuationQueryWrapper.in("goodsId", goodsIdList);
        }
        valuationQueryWrapper.orderByDesc("addDate");
        List<Valuation> valuationList = valuationService.list(valuationQueryWrapper, valuationPage);
        for (Valuation valuation1 : valuationList) {
            valuation1.setGoods(goodsService.findById(valuation1.getGoodsId()));
            valuation1.setCustomer(customerService.findById(valuation1.getCustomerId()));
        }
        Long total = valuationService.getCount(valuationQueryWrapper);
        map.put("valuationList", valuationList);
        map.put("total", total);
        return R.ok(map);
    }

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

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(Integer id) {
        int key = valuationService.deleteById(id);
        if (key > 0) {
            return R.ok("删除成功");
        } else {
            return R.ok("删除失败");
        }
    }
}
