package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.BigType;
import com.ledao.mapper.BigTypeMapper;
import com.ledao.service.BigTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品大类Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 21:44
 */
@Service("bigTypeService")
public class BigTypeServiceImpl implements BigTypeService {

    @Resource
    private BigTypeMapper bigTypeMapper;

    @Override
    public List<BigType> list(QueryWrapper<BigType> queryWrapper, Page<BigType> bigTypePage) {
        return bigTypeMapper.selectPage(bigTypePage, queryWrapper).getRecords();
    }

    @Override
    public List<BigType> list(QueryWrapper<BigType> queryWrapper) {
        return bigTypeMapper.selectList(queryWrapper);
    }

    @Override
    public Long getCount(QueryWrapper<BigType> queryWrapper) {
        return bigTypeMapper.selectCount(queryWrapper);
    }

    @Override
    public int add(BigType bigType) {
        return bigTypeMapper.insert(bigType);
    }

    @Override
    public int update(BigType bigType) {
        return bigTypeMapper.updateById(bigType);
    }

    @Override
    public int deleteById(Integer id) {
        return bigTypeMapper.deleteById(id);
    }

    @Override
    public BigType findById(Integer id) {
        return bigTypeMapper.selectById(id);
    }
}
