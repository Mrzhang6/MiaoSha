package com.zd.seckill.service.impl;

import com.zd.seckill.entity.SeckillGoods;
import com.zd.seckill.mapper.SeckillGoodsMapper;
import com.zd.seckill.service.ISeckillGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements ISeckillGoodsService {

    @Override
    public boolean updateStockCount(Long goodsId) {
        return baseMapper.updateStockCount(goodsId);
    }
}
