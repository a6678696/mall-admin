package com.ledao.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;
import com.google.gson.JsonParser;
import com.ledao.entity.Customer;
import com.ledao.entity.R;
import com.ledao.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 顾客Controller层
 *
 * @author LeDao
 * @company
 * @create 2022-12-20 23:00
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     * 顾客登录
     *
     * @param loginCode
     * @return
     */
    @PostMapping("/login")
    public R login(String loginCode) {
        Map<String, Object> map = new HashMap<>(16);
        //根据顾客登录获得的code获得openid
        HashMap<String, Object> paramMap = new HashMap<>(16);
        String settingFileLocation = "E://data/mall/myConfig.setting";
        Setting setting = new Setting(settingFileLocation);
        paramMap.put("appid", setting.getStr("appid"));
        paramMap.put("secret", setting.getStr("secret"));
        paramMap.put("js_code", loginCode);
        paramMap.put("grant_type", "authorization_code");
        String result = HttpUtil.post("https://api.weixin.qq.com/sns/jscode2session", paramMap);
        //使用Gson解析并获取openid
        String openid = new JsonParser().parse(result).getAsJsonObject().get("openid").getAsString();
        Customer customer = customerService.findByLoginCode(openid);
        //顾客第一次使用小程序,把openid保存到数据库
        if (ObjectUtil.isNull(customer)) {
            Customer customer2 = new Customer();
            customer2.setNickName("默认昵称" + System.currentTimeMillis());
            customer2.setOpenid(openid);
            customer2.setAvatarImageName("default.jpg");
            customerService.add(customer2);
            customer2.setOpenid("");
            map.put("customer", customer2);
        } else {
            customer.setOpenid("");
            map.put("customer", customer);
        }
        return R.ok(map);
    }
}
