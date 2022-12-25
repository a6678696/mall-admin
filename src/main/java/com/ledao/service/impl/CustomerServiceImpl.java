package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ledao.entity.Customer;
import com.ledao.mapper.CustomerMapper;
import com.ledao.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 顾客Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-20 22:06
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> list(QueryWrapper<Customer> customerQueryWrapper, Page<Customer> customerPage) {
        return customerMapper.selectPage(customerPage,customerQueryWrapper).getRecords();
    }

    @Override
    public Long getTotal(QueryWrapper<Customer> customerQueryWrapper) {
        return customerMapper.selectCount(customerQueryWrapper);
    }

    @Override
    public int add(Customer customer) {
        return customerMapper.insert(customer);
    }

    @Override
    public int update(Customer customer) {
        return customerMapper.updateById(customer);
    }

    @Override
    public int detele(Integer id) {
        return customerMapper.deleteById(id);
    }

    @Override
    public Customer findById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public Customer findByLoginCode(String openid) {
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq("openid", openid);
        return customerMapper.selectOne(customerQueryWrapper);
    }
}
