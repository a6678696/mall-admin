package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Address;

import java.util.List;

/**
 * 收货地址Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-25 15:30
 */
public interface AddressService {

    /**
     * 不分页条件查询收货地址
     *
     * @param addressQueryWrapper
     * @return
     */
    List<Address> list(QueryWrapper<Address> addressQueryWrapper);

    /**
     * 添加
     *
     * @param address
     * @return
     */
    int add(Address address);

    /**
     * 修改
     *
     * @param address
     * @return
     */
    int update(Address address);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Integer id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Address findById(Integer id);
}
