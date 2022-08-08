package com.zd.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zd
 * @date 2022/4/27 22:22
 * 描述：
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    //通用
    SUCESS(200,"SUCESS"),
    ERROR(500,"服务端异常"),

    //登录
    LOGIN_ERROR(500210,"用户名或密码错误"),
    MOBILE_ERROR(500211,"手机号码格式不正确"),
    MOBIEL_NOT_EXIST(500212,"手机号不存早"),
    PASSWORD_UPDATE_FAIL(500213,"更新密码错误"),
    SESSION_ERROR(500214,"登录错误"),
    ERROR_CAPTCHA(500215,"验证码错误"),
    ERROR_LIMIT(500216,"访问过于频繁，请稍后再试"),
    //绑定ERROR
    BIND_ERROR(500212,"参数校验异常"),

    //秒杀5005xx
    EMPTY_STOCK(500500,"库存不足"),
    REPEATE_ERROT(500501,"该商品没人限购一件"),
    REQUEST_ILLEGAL(500502,"请求非法，请重试"),

    //订单模块5003xxx
    ORDER_NOT_EXIST(500300,"订单信息不存在")



    ;


    private final Integer code;
    private final String message;
}
