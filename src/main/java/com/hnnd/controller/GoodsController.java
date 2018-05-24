package com.hnnd.controller;

import com.hnnd.Redis.GoodsKey;
import com.hnnd.constants.MDA;
import com.hnnd.entity.SSkillUser;
import com.hnnd.enumuration.SSkillStatus;
import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.service.RedisService;
import com.hnnd.service.SSkillGoodsService;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.util.ThymeleafUtil;
import com.hnnd.vo.GoodsDetailVo;
import com.hnnd.vo.GoodsVo;
import com.hnnd.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 商品controller
 * Created by 85073 on 2018/3/31.
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private SSkillGoodsService sSkillGoodsServiceImpl;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    RedisService redisServiceImpl;

    /**
     * 优化:加入页面级缓存
     * 跳转到商品列表页面
     * @return 列表页面
     */
    @RequestMapping(value = "/toList",produces = "text/html")
    @ResponseBody
    public String toListPage(HttpServletRequest request, HttpServletResponse response,
                             SSkillUser sSkillUser, Model model) {
        //校验参数
        logger.info("sskillUser{}",sSkillUser);
        //取缓存
       String html =redisServiceImpl.get(GoodsKey.generaGoodsList,"",String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
        model.addAttribute("sskillUser",sSkillUser);
        List<GoodsVo> goodsVoList = sSkillGoodsServiceImpl.queryGoodsVoList();
        model.addAttribute("goodsVoList",goodsVoList);

        html = ThymeleafUtil.pageCacheHtml(thymeleafViewResolver,request,response,model,"goodsList");
        if(!StringUtils.isEmpty(html)) {
            redisServiceImpl.set(GoodsKey.generaGoodsList,"",html);
        }

        return  html;
    }

    /**
     * 加入url级别的缓存
     * @param sSkillUser 秒杀用户对象
     * @param model model
     * @param id  商品id
     * @param request request
     * @param response response
     * @return hmtl
     */
    @RequestMapping(value = "/toDetail/{id}",produces = "text/html")
    public String toDetail(SSkillUser sSkillUser, Model model, @PathVariable("id") long id,
                           HttpServletRequest request, HttpServletResponse response){
        logger.info("sskillUser{}",sSkillUser);
        //取缓存
        String html =redisServiceImpl.get(GoodsKey.generaGoodDetail,id+"",String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }

        GoodsVo goodsVo = sSkillGoodsServiceImpl.queryGoodsVoDetails(id);
        if(goodsVo == null) {
            logger.error("商品不存在goodsId:{}",id);
            throw new SSkillException(SystemCode.GOODS_NOT_EXISTS);
        }
        model.addAttribute("goods",goodsVo);
        model.addAttribute("sskillUser",sSkillUser);

        long sskillStartTime = goodsVo.getSskillStartTime().getTime();
        long sskillEndTime = goodsVo.getSskillEndTime().getTime();
        int sskillStatus = getSskillStatus(sskillStartTime,sskillEndTime);
        int remainSeconds = getRemainSeconds(sskillStartTime,sskillEndTime);

        model.addAttribute("sskillStatus", sskillStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        html = ThymeleafUtil.pageCacheHtml(thymeleafViewResolver,request,response,model,"goodsDetail");
        if(!StringUtils.isEmpty(html)) {
            redisServiceImpl.set(GoodsKey.generaGoodDetail,id+"",html);
        }
        return html;
    }

    /**
     * 用于前后端分离  ajax来获取json对象
     * @param sSkillUser 用户对象
     * @param id 商品id
     * @return ResultVo<GoodsDetailVo>
     */
    @RequestMapping(value = "/toDetail2/{id}")
    @ResponseBody
    public ResultVo<GoodsDetailVo> toDetail2(SSkillUser sSkillUser ,@PathVariable("id") long id){

        if(sSkillUser == null) {
            logger.info("用户没有登录");
            throw new SSkillException(SystemCode.USER_NOT_LOGIN);
        }

        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setsSkillUser(sSkillUser);

        GoodsVo goodsVo = sSkillGoodsServiceImpl.queryGoodsVoDetails(id);
        if(goodsVo == null) {
            logger.error("商品不存在goodsId:{}",id);
            throw new SSkillException(SystemCode.GOODS_NOT_EXISTS);
        }
        goodsDetailVo.setGoodsVo(goodsVo);

        long sskillStartTime = goodsVo.getSskillStartTime().getTime();
        long sskillEndTime = goodsVo.getSskillEndTime().getTime();
        int sskillStatus = getSskillStatus(sskillStartTime,sskillEndTime);
        int remainSeconds = getRemainSeconds(sskillStartTime,sskillEndTime);

        goodsDetailVo.setSskillStatus(sskillStatus);
        goodsDetailVo.setRemainSeconds(remainSeconds);

        return ResultVoUtils.success(goodsDetailVo);
    }

    /**
     *
     * @param beginTime  开始时间
     * @param endTime  结束时间
     * @return  秒杀状态
     */
    private Integer getSskillStatus(long beginTime,long endTime) {
        int sskillStatus;
        long currentTime = System.currentTimeMillis();
        if(currentTime<beginTime) {
            sskillStatus = SSkillStatus.SSKILL_NOT_BEGIN.getCode();
        }else if(currentTime>endTime){
            sskillStatus = SSkillStatus.SSKILL_IS_OVER.getCode();
        }else {
            sskillStatus = SSkillStatus.SSKILL_IN_TIME.getCode();
        }
        return sskillStatus;
    }

    /**
     * 计算秒杀剩余时间
     * @param beginTime 秒杀开始时间
     * @param endTime 秒杀结束时间
     * @return 秒杀倒计时
     */
    private Integer getRemainSeconds(long beginTime,long endTime) {
        int remainSeconds;
        long currentTime = System.currentTimeMillis();
        if(currentTime<beginTime) {
             remainSeconds = (int)(beginTime-currentTime)/1000;
        }else if(currentTime>endTime){
            remainSeconds = MDA.SSKILL_OVER_TIME_FLAG;
        }else {
            remainSeconds = MDA.SSKILL_IN_TIME_FLAG;
        }
        return remainSeconds;
    }
}
