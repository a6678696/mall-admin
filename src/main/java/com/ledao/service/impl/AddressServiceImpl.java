package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Address;
import com.ledao.mapper.AddressMapper;
import com.ledao.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-25 15:31
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    @Override
    public List<Address> list(QueryWrapper<Address> addressQueryWrapper) {
        return addressMapper.selectList(addressQueryWrapper);
    }

    @Override
    public int add(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public int update(Address address) {
        return addressMapper.updateById(address);
    }

    @Override
    public int delete(Integer id) {
        return addressMapper.deleteById(id);
    }

    @Override
    public Address findById(Integer id) {
        return addressMapper.selectById(id);
    }
}
