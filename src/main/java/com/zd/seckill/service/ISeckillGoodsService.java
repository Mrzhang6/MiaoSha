package com.zd.seckill.service;

import com.zd.seckill.entity.SeckillGoods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface ISeckillGoodsService extends IService<SeckillGoods> {

    boolean updateStockCount(Long id);
}
