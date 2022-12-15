package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.BigType;

import java.util.List;

/**
 * 商品大类Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 21:37
 */
public interface BigTypeService {

    /**
     * 分页条件查询商品大类
     *
     * @param queryWrapper
     * @param bigTypePage
     * @return
     */
    List<BigType> list(QueryWrapper<BigType> queryWrapper, Page<BigType> bigTypePage);

    /**
     * 不分页条件查询商品大类
     *
     * @param queryWrapper
     * @return
     */
    List<BigType> list(QueryWrapper<BigType> queryWrapper);

    /**
     * 获取记录数
     *
     * @param queryWrapper
     * @return
     */
    Long getCount(QueryWrapper<BigType> queryWrapper);

    /**
     * 添加商品大类
     *
     * @param bigType
     * @return
     */
    int add(BigType bigType);

    /**
     * 修改商品大类
     *
     * @param bigType
     * @return
     */
    int update(BigType bigType);

    /**
     * 根据id删除商品大类
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据id查找商品大类
     *
     * @param id
     * @return
     */
    BigType findById(Integer id);
}
