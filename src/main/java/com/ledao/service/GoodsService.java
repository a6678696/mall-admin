package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Goods;

import java.util.List;

/**
 * 商品Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-16 13:27
 */
public interface GoodsService {

    /**
     * 分页条件查询商品
     *
     * @param goodsQueryWrapper
     * @param goodsPage
     * @return
     */
    List<Goods> list(QueryWrapper<Goods> goodsQueryWrapper, Page<Goods> goodsPage);

    /**
     * 不分页条件查询商品
     *
     * @param goodsQueryWrapper
     * @return
     */
    List<Goods> list(QueryWrapper<Goods> goodsQueryWrapper);

    /**
     * 获取记录数
     *
     * @param goodsQueryWrapper
     * @return
     */
    Long getCount(QueryWrapper<Goods> goodsQueryWrapper);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    int add(Goods goods);

    /**
     * 修改商品
     *
     * @param goods
     * @return
     */
    int update(Goods goods);

    /**
     * 根据id删除商品
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据id查找商品
     *
     * @param id
     * @return
     */
    Goods findById(Integer id);
}
