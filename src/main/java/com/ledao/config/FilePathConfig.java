package com.ledao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 本地文件映射路径配置类
 *
 * @author LeDao
 * @company
 * @create 2022-12-08 3:08
 */
@Configuration
public class FilePathConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //商品卡片的图片
        registry.addResourceHandler("/image/goods/card/**").addResourceLocations("file:E:/data/mall/images/goods/card/");
        //商品详情图片
        registry.addResourceHandler("/image/goods/details/**").addResourceLocations("file:E:/data/mall/images/goods/details/");
        //商品详情的轮播图图片
        registry.addResourceHandler("/image/goods/swiper/**").addResourceLocations("file:E:/data/mall/images/goods/swiper/");
        //顾客头像图片
        registry.addResourceHandler("/image/customer/avatar/**").addResourceLocations("file:E:/data/mall/images/customer/");
    }
}
