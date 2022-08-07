package com.zd.seckill.service;

import com.zd.seckill.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zd.seckill.vo.GoodsVo;

import java.util.List;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
