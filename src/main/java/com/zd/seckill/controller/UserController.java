package com.zd.seckill.controller;

import com.zd.seckill.entity.User;
import com.zd.seckill.rabbitmq.MQSender;
import com.zd.seckill.vo.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@RestController
@RequestMapping("/user")
@Api(value = "",tags = "")
public class UserController {
    @Autowired
    MQSender mqSender;

    /**
     * 获取用户信息(测试)
     * @param user
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }


    /**
     * 测试发送Rabbitmq消息
     *//*
    @RequestMapping("/mq")
    @ResponseBody
    public void mq(){
        mqSender.send("hello");
    }

    *//**
     * 测试Fanout模式
     *//*
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public void mq01(){
        mqSender.send("hello");
    }

    *//**
     * 测试direct模式
     *//*
    @RequestMapping("/mq/direct01")
    @ResponseBody
    public void mq02(){
        mqSender.send01("hello red");
    }

    *//**
     * 测试direct模式
     *//*
    @RequestMapping("/mq/direct02")
    @ResponseBody
    public void mq03(){
        mqSender.send02("hello green");
    }

    *//**
     * 测试topic模式
     *//*
    @RequestMapping("/mq/topic01")
    @ResponseBody
    public void mq04(){
        mqSender.send03("hello green");
    }

    *//**
     * 测试topic模式
     *//*
    @RequestMapping("/mq/topic02")
    @ResponseBody
    public void mq05(){
        mqSender.send04("hello green");
    }

*/


}
