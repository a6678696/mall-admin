package com.ledao.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;
import com.google.gson.JsonParser;
import com.ledao.entity.Customer;
import com.ledao.entity.R;
import com.ledao.service.CustomerService;
import com.ledao.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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

    @Value("${customerAvatarImageFilePath}")
    private String customerAvatarImageFilePath;

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

    /**
     * 顾客选择图片后上传到服务器
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadImage")
    public String uploadImage(MultipartFile file) throws Exception {
        //给图片定义一个名称
        String newFileName = DateUtil.getCurrentDateStr2() + System.currentTimeMillis() + ".jpg";
        assert file != null;
        //实现将图片保存到指定位置
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(customerAvatarImageFilePath + newFileName));
        return "http://localhost:8080/image/customer/avatar/" + newFileName;
    }

    /**
     * 顾客删除图片后从服务器中删除
     *
     * @param url
     */
    @GetMapping("/deleteImage")
    public void deleteImage(String url) {
        String imageName = url.replaceAll("http://localhost:8080/image/customer/avatar/", "");
        FileUtils.deleteQuietly(new File(customerAvatarImageFilePath + imageName));
    }

    /**
     * 修改个人信息
     *
     * @param id
     * @param nickName
     * @param url
     * @return
     */
    @PostMapping("/update")
    public R update(Integer id, String nickName, String url) {
        Customer customer = customerService.findById(id);
        customer.setNickName(nickName);
        if (!"".equals(url)) {
            //顾客修改后的头像名称
            String imageName = url.replaceAll("http://localhost:8080/image/customer/avatar/", "");
            //原图片不是默认图片就删除
            if (!"default.jpg".equals(customer.getAvatarImageName())) {
                FileUtils.deleteQuietly(new File(customerAvatarImageFilePath + customer.getAvatarImageName()));
            }
            customer.setAvatarImageName(imageName);
        }
        customerService.update(customer);
        Map<String, Object> map = new HashMap<>(16);
        map.put("customer", customer);
        return R.ok(map);
    }
}
