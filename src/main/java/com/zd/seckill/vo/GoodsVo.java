package com.zd.seckill.vo;

import com.zd.seckill.entity.Goods;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zd
 * @date 2022/5/5 15:46
 * 描述：商品返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo extends Goods {

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;
}
