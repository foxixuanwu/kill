package com.debug.kill.server.service;

import com.debug.kill.model.entity.ItemKill;

import java.util.List;

/**
 * @author User
 * @Title: ItemService
 * @ProjectName kill
 * @Description: TODO
 * @date 2020/6/30 15:06
 */
public interface IItemService {

   //获取待秒杀商品列表
   List<ItemKill> getKillItems() throws Exception;

}
