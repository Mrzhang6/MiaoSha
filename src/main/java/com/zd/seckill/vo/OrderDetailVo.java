package com.zd.seckill.vo;

import com.zd.seckill.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zd
 * @date 2022/5/11 21:06
 * 描述：订单详情返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {

    private Order order;

    private GoodsVo goodsVo;

}
