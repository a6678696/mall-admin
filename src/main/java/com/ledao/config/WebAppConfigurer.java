package com.ledao.config;

import com.ledao.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 实现跨域
 *
 * @author LeDao
 * @company
 * @create 2022-12-13 3:27
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    /**
     * 实现跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    /**
     * 配置本地映射
     *
     * @param registry
     */
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

    @Bean
    public SysInterceptor sysInterceptor() {
        return new SysInterceptor();
    }

    /**
     * 设置不拦截的路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不拦截的路径
        String[] patterns = new String[]{"/administrator/login", "/customer/login", "/token/check"};
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
