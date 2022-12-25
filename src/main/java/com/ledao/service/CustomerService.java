package com.ledao.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Customer;

import java.util.List;

/**
 * 顾客Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-20 22:06
 */
public interface CustomerService {

    /**
     * 分页条件查询顾客信息
     *
     * @param customerQueryWrapper
     * @param customerPage
     * @return
     */
    List<Customer> list(QueryWrapper<Customer> customerQueryWrapper, Page<Customer> customerPage);

    /**
     * 获取记录数
     *
     * @param customerQueryWrapper
     * @return
     */
    Long getTotal(QueryWrapper<Customer> customerQueryWrapper);

    /**
     * 添加顾客信息
     *
     * @param customer
     * @return
     */
    int add(Customer customer);

    /**
     * 修改顾客信息
     *
     * @param customer
     * @return
     */
    int update(Customer customer);

    /**
     * 删除顾客信息
     *
     * @param id
     * @return
     */
    int detele(Integer id);

    /**
     * 根据id查找顾客
     *
     * @param id
     * @return
     */
    Customer findById(Integer id);

    /**
     * 根据微信用户登录时返回的code查找顾客
     *
     * @param openid
     * @return
     */
    Customer findByLoginCode(String openid);
}
