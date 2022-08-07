package com.zd.seckill.controller;

import com.zd.seckill.entity.User;
import com.zd.seckill.service.IGoodsService;
import com.zd.seckill.service.IUserService;
import com.zd.seckill.vo.DetialVo;
import com.zd.seckill.vo.GoodsVo;
import com.zd.seckill.vo.RespBean;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * @author 作者
 * @date 2022-04-27
 */
@Controller
@RequestMapping("/goods")
@Api(value = "",tags = "")
public class GoodsController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    /**
     * 跳转商品列表页
     * windows优化前QPS：1332
     * Linux优化前QPS：207
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/toList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toList( Model model, User user,
                          HttpServletRequest request,HttpServletResponse response){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if(!StringUtils.isEmpty(html)){
            //如果不为空，直接返回页面
            return html;
        }
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        //如果为空手动渲染，存入redis并且返回
        WebContext context = new WebContext(request,response,request.getServletContext(),request.getLocale(),
                    model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsList", context);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodsList",html,60, TimeUnit.SECONDS);
        }
        return html;
    }

    /**
     * 跳转商品详情页
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/toDetail2/{goodsId}",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toDetail2(Model model, User user, @PathVariable Long goodsId,HttpServletRequest request,HttpServletResponse
                           response){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsDetail:" + goodsId);

        if(!StringUtils.isEmpty(html)){
            return html;
        }
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        if(nowDate.before(startDate)){
            remainSeconds = (int)((startDate.getTime() - nowDate.getTime())/1000);
        }else if(nowDate.after(endDate)){
            //秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        }else {
            //描述进行中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("goods",goodsVo);
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail", context);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodsDetail:" + goodsId,html,60,TimeUnit.SECONDS);
        }
        return html;
    }

    /**
     * 跳转商品详情页
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/toDetail/{goodsId}")
    @ResponseBody
    public RespBean toDetail(Model model, User user, @PathVariable Long goodsId){
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;
        if(nowDate.before(startDate)){
            remainSeconds = (int)((startDate.getTime() - nowDate.getTime())/1000);
        }else if(nowDate.after(endDate)){
            //秒杀已结束
            secKillStatus = 2;
            remainSeconds = -1;
        }else {
            //描述进行中
            secKillStatus = 1;
            remainSeconds = 0;
        }
        DetialVo detialVo = new DetialVo();
        detialVo.setUser(user);
        detialVo.setGoodsVo(goodsVo);
        detialVo.setSecKillStatus(secKillStatus);
        detialVo.setRemainSeconds(remainSeconds);
        return RespBean.success(detialVo);
    }

}
