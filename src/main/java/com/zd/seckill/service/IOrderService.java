package com.zd.seckill.service;

import com.zd.seckill.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.seckill.entity.User;
import com.zd.seckill.vo.GoodsVo;
import com.zd.seckill.vo.OrderDetailVo;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface IOrderService extends IService<Order> {

    /**
     * 秒杀
     * @param user
     * @param goods
     * @return
     */

    Order seckill(User user, GoodsVo goods);

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    OrderDetailVo detail(Long orderId);
}
