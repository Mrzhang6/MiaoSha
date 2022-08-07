package com.zd.seckill.mapper;

import com.zd.seckill.entity.SeckillGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoods> {

    boolean updateStockCount(Long goodsId);

}
