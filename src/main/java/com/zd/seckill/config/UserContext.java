package com.zd.seckill.config;

import com.zd.seckill.entity.User;

/**
 * @author zd
 * @date 2022/8/8 15:35
 * 描述：
 */
public class UserContext {

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser(){
        return userHolder.get();
    }
}
