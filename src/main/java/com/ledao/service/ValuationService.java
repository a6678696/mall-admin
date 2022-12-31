package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Valuation;

import java.util.List;

/**
 * 评价Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-31 19:28
 */
public interface ValuationService {

    /**
     * 添加评价
     *
     * @param valuation
     * @return
     */
    int add(Valuation valuation);

    /**
     * 不分页条件查询评价
     *
     * @param valuationQueryWrapper
     * @return
     */
    List<Valuation> list(QueryWrapper<Valuation> valuationQueryWrapper);
}
