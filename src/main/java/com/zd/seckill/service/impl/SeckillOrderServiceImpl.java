package com.zd.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zd.seckill.entity.SeckillOrder;
import com.zd.seckill.entity.User;
import com.zd.seckill.mapper.SeckillOrderMapper;
import com.zd.seckill.service.ISeckillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {


    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 获取秒杀结果
     * @param user
     * @param goodsId
     * @return
     */
    @Override
    public Long getResult(User user, Long goodsId) {

        SeckillOrder seckillOrder = this.baseMapper.selectOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId()).
                eq("goods_id", goodsId));
        if( null != seckillOrder){
            return seckillOrder.getOrderId();
        }else if(redisTemplate.hasKey("isStockEmpty:"+goodsId)){
            return  -1L;
        }else {
            return 0L;
        }

    }
}
