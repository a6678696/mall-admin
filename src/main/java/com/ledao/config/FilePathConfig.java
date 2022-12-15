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
        registry.addResourceHandler("/swiper/image/**").addResourceLocations("file:E:/data/mall/images/swiper/");
    }
}
