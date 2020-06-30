package com.debug.kill.server.controller;

import com.debug.kill.model.entity.ItemKill;
import com.debug.kill.server.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author User
 * @Title: ItemController
 * @ProjectName kill
 * @Description: TODO
 * @date 2020/6/30 14:58
 */
@Controller
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private static final String prefix = "item";

    @Autowired
    private IItemService itemService;

    /**
     * 获取商品列表
     */
    @GetMapping(value = {"/","/index",prefix+"/list",prefix+"/index.html"})
    public String list(ModelMap modelMap){
        try {
            //获取待秒杀商品列表
            List<ItemKill> list = itemService.getKillItems();
            modelMap.put("list",list);

            logger.info("获取待秒杀商品列表-数据：{}",list);

        }catch (Exception e){
            logger.error("获取待秒杀商品列表-发生异常：",e.fillInStackTrace());
            return "redirect:/base/error";
        }
        return "list";
    }

    /**
     * 获取待秒杀商品的详情
     * @return
     */
    @GetMapping(value = {prefix+"/detail/{id}"})
    public String detail(@PathVariable Integer id,ModelMap modelMap){
        if (id == null && id <=0) {
            return "redirect:/base/error";
        }
        try {
            ItemKill killDetail = itemService.getKillDetail(id);
            modelMap.put("detail",killDetail);

        }catch (Exception e){
            logger.error("获取待秒杀商品详情-发生异常：id={}",id,e.fillInStackTrace());
            return "redirect:/base/error";
        }
        return "info";
    }

}
