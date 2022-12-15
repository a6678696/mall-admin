package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.SmallType;
import com.ledao.mapper.SmallTypeMapper;
import com.ledao.service.SmallTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品小类Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 22:57
 */
@Service("smallTypeService")
public class SmallTypeServiceImpl implements SmallTypeService {

    @Resource
    private SmallTypeMapper smallTypeMapper;

    @Override
    public List<SmallType> list(QueryWrapper<SmallType> queryWrapper, Page<SmallType> smallTypePage) {
        return smallTypeMapper.selectPage(smallTypePage, queryWrapper).getRecords();
    }

    @Override
    public List<SmallType> list(QueryWrapper<SmallType> queryWrapper) {
        return smallTypeMapper.selectList(queryWrapper);
    }

    @Override
    public Long getCount(QueryWrapper<SmallType> queryWrapper) {
        return smallTypeMapper.selectCount(queryWrapper);
    }

    @Override
    public int add(SmallType smallType) {
        return smallTypeMapper.insert(smallType);
    }

    @Override
    public int update(SmallType smallType) {
        return smallTypeMapper.updateById(smallType);
    }

    @Override
    public int deleteById(Integer id) {
        return smallTypeMapper.deleteById(id);
    }

    @Override
    public SmallType findById(Integer id) {
        return smallTypeMapper.selectById(id);
    }
}
