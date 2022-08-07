package com.zd.seckill.controller;

import com.zd.seckill.entity.User;
import com.zd.seckill.service.IOrderService;
import com.zd.seckill.vo.OrderDetailVo;
import com.zd.seckill.vo.RespBean;
import com.zd.seckill.vo.RespBeanEnum;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@RestController
@RequestMapping("/order")
@Api(value = "",tags = "")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 订单详情
     * @param user
     * @param
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user,Long orderId){
        if(user == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        OrderDetailVo detail = orderService.detail(orderId);

        return RespBean.success(detail);

    }

}
