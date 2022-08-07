package com.zd.seckill.vo;

import com.zd.seckill.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zd
 * @date 2022/5/10 20:47
 * 描述：详情返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetialVo {

    private User user;
    private GoodsVo goodsVo;

    private int secKillStatus;

    private int remainSeconds;
}
