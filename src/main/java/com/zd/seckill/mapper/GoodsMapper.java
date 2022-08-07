package com.zd.seckill.mapper;

import com.zd.seckill.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zd.seckill.vo.GoodsVo;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author 作者
 * @date 2022-04-27
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
