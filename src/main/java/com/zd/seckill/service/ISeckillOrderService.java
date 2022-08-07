package com.zd.seckill.service;

import com.zd.seckill.entity.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.seckill.entity.User;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getResult(User user, Long goodsId);
}
