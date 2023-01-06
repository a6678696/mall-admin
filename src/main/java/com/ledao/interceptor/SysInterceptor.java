package com.ledao.interceptor;

import cn.hutool.core.util.StrUtil;
import com.ledao.util.JwtUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义鉴权拦截器
 *
 * @author LeDao
 * @company
 * @create 2023-01-07 4:07
 */
public class SysInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            //获取请求头的token
            String token = request.getHeader("token");
            //当token为空
            if (StrUtil.isEmpty(token)) {
                throw new RuntimeException("token为空");
            } else {
                //token验证成功
                if (JwtUtil.checkToken(token)) {
                    return true;
                } else {
                    throw new RuntimeException("token验证失败");
                }
            }
        } else {
            return true;
        }
    }
}
