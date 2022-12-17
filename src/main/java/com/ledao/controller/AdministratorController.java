package com.ledao.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ledao.entity.Administrator;
import com.ledao.entity.R;
import com.ledao.service.AdministratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-05 20:27
 */
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Resource
    private AdministratorService administratorService;

    /**
     * 管理员登录
     *
     * @param administrator
     * @return
     */
    @GetMapping("/login")
    public R login(Administrator administrator) {
        //没有输入用户名时
        if (StrUtil.isEmpty(administrator.getUserName())) {
            return R.error("请输入用户名");
        }
        //没有输入密码时
        if (StrUtil.isEmpty(administrator.getPassword())) {
            return R.error("请输入密码");
        }
        Administrator trueAdministrator = administratorService.findByUserName(administrator.getUserName());
        //用户名正确时
        if (ObjectUtil.isNull(trueAdministrator)) {
            return R.error("用户名或密码错误");
        } else {
            //密码正确时
            if (trueAdministrator.getPassword().equals(administrator.getPassword())) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("id", trueAdministrator.getId());
                map.put("userName", trueAdministrator.getUserName());
                return R.ok(map);
            } else {
                return R.error("用户名或密码错误");
            }
        }
    }

    /**
     * 修改密码
     *
     * @param administratorId
     * @param password
     * @return
     */
    @PostMapping("/modifyPassword")
    public R modifyPassword(Integer administratorId, String password) {
        Administrator administrator = administratorService.findById(administratorId);
        if (ObjectUtil.isNotNull(administrator)) {
            administrator.setPassword(password);
            administratorService.update(administrator);
            return R.ok("密码修改成功,下次登录时生效");
        } else {
            return R.error("管理员不存在");
        }
    }
}
