package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.SmallType;

import java.util.List;

/**
 * 商品小类Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-15 22:57
 */
public interface SmallTypeService {

    /**
     * 分页条件查询商品小类
     *
     * @param queryWrapper
     * @param smallTypePage
     * @return
     */
    List<SmallType> list(QueryWrapper<SmallType> queryWrapper, Page<SmallType> smallTypePage);

    /**
     * 不分页条件查询商品小类
     *
     * @param queryWrapper
     * @return
     */
    List<SmallType> list(QueryWrapper<SmallType> queryWrapper);

    /**
     * 获取记录数
     *
     * @param queryWrapper
     * @return
     */
    Long getCount(QueryWrapper<SmallType> queryWrapper);

    /**
     * 添加商品小类
     *
     * @param smallType
     * @return
     */
    int add(SmallType smallType);

    /**
     * 修改商品小类
     *
     * @param smallType
     * @return
     */
    int update(SmallType smallType);

    /**
     * 根据id删除商品小类
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 根据id查找商品小类
     *
     * @param id
     * @return
     */
    SmallType findById(Integer id);
}
