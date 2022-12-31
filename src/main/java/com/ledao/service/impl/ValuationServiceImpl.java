package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Valuation;
import com.ledao.mapper.ValuationMapper;
import com.ledao.service.ValuationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评价Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-31 19:29
 */
@Service("valuationService")
public class ValuationServiceImpl implements ValuationService {

    @Resource
    private ValuationMapper valuationMapper;

    @Override
    public int add(Valuation valuation) {
        return valuationMapper.insert(valuation);
    }

    @Override
    public List<Valuation> list(QueryWrapper<Valuation> valuationQueryWrapper) {
        return valuationMapper.selectList(valuationQueryWrapper);
    }
}
