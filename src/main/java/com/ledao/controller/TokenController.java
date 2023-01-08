package com.ledao.controller;

import cn.hutool.core.util.StrUtil;
import com.ledao.entity.R;
import com.ledao.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * token令牌Controller层
 *
 * @author LeDao
 * @company
 * @create 2023-01-07 5:10
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    /**
     * 检测token是否有效,并返回token中的roleName
     *
     * @param token
     * @return
     */
    @GetMapping("/check")
    public R check(String token) {
        if (StrUtil.isEmpty(token)) {
            return R.error("token为空");
        }
        //token验证通过时
        if (JwtUtil.checkToken(token)) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("roleName", JwtUtil.decodedJWT(token).getAudience().get(0));
            return R.ok(map);
        } else {
            return R.error("token验证失败");
        }
    }
}
