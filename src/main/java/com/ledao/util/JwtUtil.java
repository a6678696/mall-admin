package com.ledao.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;

/**
 * jwt工具类
 *
 * @author LeDao
 * @company
 * @create 2023-01-03 23:04
 */
public class JwtUtil {

    /**
     * 加密使用到的KEY
     */
    private static final String TOKEN_SECRET = "ledao";

    /**
     * 生成
     *
     * @param roleName 当前角色
     * @param time     有效时间
     * @return
     */
    public static String createToken(String roleName, int time) {
        return JWT.create()
                //编号
                .withJWTId(UUID.randomUUID().toString())
                //签发人
                .withIssuer("乐道")
                //签发时间
                .withIssuedAt(new Date(System.currentTimeMillis()))
                //过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + time))
                //受众
                .withAudience(roleName)
                //主题
                .withSubject("mall-admin")
                //创建一个新的JWT并使用给定的算法进行签名
                .sign(Algorithm.HMAC512(TOKEN_SECRET));
    }

    /**
     * 验证
     *
     * @param token
     * @return
     */
    public static boolean checkToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            //验证
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(TOKEN_SECRET)).build();
            jwtVerifier.verify(token);
        } catch (SignatureVerificationException e) {//token验证失败
            throw new RuntimeException("token验证失败");
        } catch (TokenExpiredException e) {//token已过期
            throw new RuntimeException("token已过期");
        } catch (Exception e) {//其它异常
            throw new RuntimeException(e.getMessage());
        }
        return true;
    }

    /**
     * 解码token
     *
     * @param token
     * @return
     */
    public static DecodedJWT decodedJWT(String token) {
        return JWT.decode(token);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        //生成token,有效时间为1小时
        String token = createToken("admin", 1000 * 60 * 60);
        //打印token
        System.out.println(token);
        //验证token并返回结果
        System.out.println("验证结果: " + checkToken(token));
        //打印编号
        System.out.println("编号: " + decodedJWT(token).getId());
        //打印签发时间
        System.out.println("签发时间: " + decodedJWT(token).getIssuedAt());
        //打印过期时间
        System.out.println("过期时间: " + decodedJWT(token).getExpiresAt());
        //打印签发人
        System.out.println("签发人: " + decodedJWT(token).getIssuer());
        //打印所有受众,返回一个List<String>,受众的顺序和创建JWT时的顺序有关
        System.out.println("所有受众: " + decodedJWT(token).getAudience());
        //打印受众1
        System.out.println("受众1: " + decodedJWT(token).getAudience().get(0));
    }
}