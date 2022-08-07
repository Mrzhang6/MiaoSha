package com.zd.seckill.service;

import com.zd.seckill.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.seckill.vo.LoginVo;
import com.zd.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param loginVo
     * @param request
     * @param response
     * @return
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);


    /**
     * 根据Cookie获取用户
     * @param userTicket
     * @return
     */
    User getUserByCookie(String userTicket,HttpServletRequest request, HttpServletResponse response);

    RespBean updatePassword(String userTicket,String password,HttpServletRequest request,
                            HttpServletResponse response);
}
