package com.zd.seckill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@TableName("t_seckill_goods")
@ApiModel(value = "", description = "")
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 秒杀商品ID **/
    @ApiModelProperty("秒杀商品ID")
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 商品ID **/
    @ApiModelProperty("商品ID")
    private Long goodsId;

    /** 秒杀家 **/
    @ApiModelProperty("秒杀家")
    private BigDecimal seckillPrice;

    /** 库存数量 **/
    @ApiModelProperty("库存数量")
    private Integer stockCount;

    /** 秒杀开始时间 **/
    @ApiModelProperty("秒杀开始时间")
    private Date startDate;

    /** 秒杀结束时间 **/
    @ApiModelProperty("秒杀结束时间")
    private Date endDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(BigDecimal seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SeckillGoods{" +
        "id=" + id +
        ", goodsId=" + goodsId +
        ", seckillPrice=" + seckillPrice +
        ", stockCount=" + stockCount +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        "}";
    }
}
