package com.zd.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zd.seckill.entity.Order;
import com.zd.seckill.entity.SeckillMessage;
import com.zd.seckill.entity.SeckillOrder;
import com.zd.seckill.entity.User;
import com.zd.seckill.rabbitmq.MQSender;
import com.zd.seckill.service.IGoodsService;
import com.zd.seckill.service.IOrderService;
import com.zd.seckill.service.ISeckillOrderService;
import com.zd.seckill.utils.JsonUtil;
import com.zd.seckill.vo.GoodsVo;
import com.zd.seckill.vo.RespBean;
import com.zd.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zd
 * @date 2022/5/6 10:50
 * 描述：秒杀
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController implements InitializingBean {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MQSender mqSender;

    private Map<Long, Boolean> EmptyStockMap = new HashMap<>();


    @RequestMapping("/doSeckill1")
    public String doSecKill1(Model model, User user, Long goodsId){
        if(user == null){
            return "login";
        }

        model.addAttribute("user",user);

        GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            //库存不足
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        //判断用户是否重复抢购商品
        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId())
                .eq("goods_id", goodsId));

        if(seckillOrder!=null){
            //重复抢购
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROT.getMessage());
            return "secKillFail";
        }

        Order order = orderService.seckill(user,goods);
        model.addAttribute("order",order);
        model.addAttribute("goods",goods);
        return "orderDetail";

    }

    @RequestMapping(value = "/doSeckill", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doSecKill(User user, Long goodsId){
        if(user == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        ValueOperations valueOperations = redisTemplate.opsForValue();

        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if(seckillOrder!=null){
            //重复抢购
            return RespBean.error(RespBeanEnum.REPEATE_ERROT);
        }
        //通过内存标记，减少redis访问
        if(EmptyStockMap.get(goodsId)){
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //预减库存操作，且为原子性
        Long stock = valueOperations.decrement("seckillGoods:" + goodsId);
        if(stock<0){

            EmptyStockMap.put(goodsId,true);

            //valueOperations.increment("seckillGoods:" + goodsId);

            return RespBean.error(RespBeanEnum.REPEATE_ERROT);
        }

        //下单,将消息发送给mq
        SeckillMessage seckillMessage = new SeckillMessage(user, goodsId);
        mqSender.sendSeckillMessage(JsonUtil.object2JsonStr(seckillMessage));

        return RespBean.success(0);




        /*GoodsVo goods = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            //库存不足
            //model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return RespBean.error(RespBeanEnum.EMPTY_STOCK);
        }
        //判断用户是否重复抢购商品
        //如果一个人同时发起多次请求，则可能会存在一个人同时抢购多件商品
//        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>().eq("user_id", user.getId())
//                .eq("goods_id", goodsId));

        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);

        if(seckillOrder!=null){
            //重复抢购
            //model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROT.getMessage());
            return RespBean.error(RespBeanEnum.REPEATE_ERROT);
        }

        Order order = orderService.seckill(user,goods);

        return RespBean.success(order);*/

        //return null;

    }


    /**
     * 获取秒杀结果：orderId:成功，-1：秒杀失败，0：排队中
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/getResult",method = RequestMethod.GET)
    @ResponseBody
    public RespBean getResult(User user,Long goodsId){
        if(user == null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }

        Long orderId = seckillOrderService.getResult(user,goodsId);
        return RespBean.success(orderId);
    }

    /**
     * 初始化方法，可以把商品库存数量加载到redis中去
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> list = goodsService.findGoodsVo();
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        list.forEach(goodsVo -> {

            redisTemplate.opsForValue().set("seckillGoods:"+goodsVo.getId(),goodsVo.getStockCount());
            EmptyStockMap.put(goodsVo.getId(),false);
        });
    }
}
