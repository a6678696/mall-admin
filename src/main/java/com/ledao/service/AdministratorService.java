package com.ledao.service;

import com.ledao.entity.Administrator;

/**
 * 管理员Service接口
 *
 * @author LeDao
 * @company
 * @create 2022-12-13 2:44
 */
public interface AdministratorService {

    /**
     * 根据用户名查找管理员
     *
     * @param userName
     * @return
     */
    Administrator findByUserName(String userName);

    /**
     * 根据id查找管理员
     *
     * @param id
     * @return
     */
    Administrator findById(Integer id);

    /**
     * 修改管理员信息
     *
     * @param administrator
     * @return
     */
    int update(Administrator administrator);
}
