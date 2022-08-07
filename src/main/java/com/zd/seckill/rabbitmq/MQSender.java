package com.zd.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zd
 * @date 2022/5/12 17:00
 * 描述：消息发送者
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

   /* public void send(Object msg){
        log.info("发送消息："+msg);

        rabbitTemplate.convertAndSend("fanoutExchange","",msg);
    }

    public void send01(Object msg){
        log.info("发送red消息："+msg);

        rabbitTemplate.convertAndSend("directExchange","queue.red",msg);
    }

    public void send02(Object msg){
        log.info("发送green消息："+msg);

        rabbitTemplate.convertAndSend("directExchange","queue.green",msg);
    }

    public void send03(Object msg){
        log.info("发送QUEUE01接收的消息："+msg);

        rabbitTemplate.convertAndSend("topicExchange","queue.red.message",msg);
    }

    public void send04(Object msg){
        log.info("发送两个queue接收的消息："+msg);

        rabbitTemplate.convertAndSend("topicExchange","message.queue.green.message",msg);
    }*/

    /**
     * 发送秒杀信息
     * @param message
     */
    public void sendSeckillMessage(String message){
        log.info("message"+message);
        rabbitTemplate.convertAndSend("seckillExchange","seckill.message",message);

    }

}
