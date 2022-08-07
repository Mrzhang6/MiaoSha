package com.zd.seckill.rabbitmq;

import com.zd.seckill.entity.SeckillMessage;
import com.zd.seckill.entity.SeckillOrder;
import com.zd.seckill.entity.User;
import com.zd.seckill.service.IGoodsService;
import com.zd.seckill.service.IOrderService;
import com.zd.seckill.utils.JsonUtil;
import com.zd.seckill.vo.GoodsVo;
import com.zd.seckill.vo.RespBean;
import com.zd.seckill.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zd
 * @date 2022/5/12 17:16
 * 描述：消息消费者
 */
@Service
@Slf4j
public class MQRecevier {

//    @RabbitListener(queues = "queue")
//    public void receive(Object msg){
//        log.info("接收消息:"+msg);
//        //System.out.println(msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout01")
//    public void receive01(Object msg){
//        log.info("QUEUE01接收消息:"+msg);
//        //System.out.println(msg);
//    }
//
//    @RabbitListener(queues = "queue_fanout02")
//    public void receive02(Object msg){
//        log.info("QUEUE02" +
//                "接收消息:"+msg);
//        //System.out.println(msg);
//    }
//
//    @RabbitListener(queues = "queue_direct01")
//    public void receive03(Object msg){
//        log.info("queue_direct01" +
//                "接收消息:"+msg);
//        //System.out.println(msg);
//    }
//
//    @RabbitListener(queues = "queue_direct02")
//    public void receive04(Object msg){
//        log.info("queue_direct02" +
//                "接收消息:"+msg);
//        //System.out.println(msg);
//    }
//
//    @RabbitListener(queues = "queue_topic01")
//    public void receive05(Object msg){
//        log.info("queue1" +
//                "接收消息:"+msg);
//        //System.out.println(msg);
//    }
//
//    @RabbitListener(queues = "queue_topic02")
//    public void receive06(Object msg){
//        log.info("queue2" +
//                "接收消息:"+msg);
//        //System.out.println(msg);
//    }
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IOrderService orderService;

    /**
     * 就收消息之后进行下单操作
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message){
        log.info("接收到的消息",message);

        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodId = seckillMessage.getGoodId();
        User user = seckillMessage.getUser();

        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodId);
        if(goodsVo.getStockCount()<1){
            return;
        }

        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodId);
        if(seckillOrder!=null){
            //重复抢购
            return;
        }
        //下单操作
        orderService.seckill(user,goodsVo);




    }

}
