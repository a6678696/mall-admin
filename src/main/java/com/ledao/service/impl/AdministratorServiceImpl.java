package com.ledao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ledao.entity.Administrator;
import com.ledao.mapper.AdministratorMapper;
import com.ledao.service.AdministratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员Service接口实现类
 *
 * @author LeDao
 * @company
 * @create 2022-12-13 2:47
 */
@Service("administratorService")
public class AdministratorServiceImpl implements AdministratorService {

    @Resource
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator findByUserName(String userName) {
        QueryWrapper<Administrator> administratorQueryWrapper = new QueryWrapper<>();
        administratorQueryWrapper.eq("userName", userName);
        return administratorMapper.selectOne(administratorQueryWrapper);
    }

    @Override
    public Administrator findById(Integer id) {
        return administratorMapper.selectById(id);
    }

    @Override
    public int update(Administrator administrator) {
        return administratorMapper.updateById(administrator);
    }
}
