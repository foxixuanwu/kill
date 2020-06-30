package com.debug.kill.server.service.impl;

import com.debug.kill.model.entity.ItemKill;
import com.debug.kill.model.mapper.ItemKillMapper;
import com.debug.kill.server.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author User
 * @Title: ItemServiceImpl
 * @ProjectName kill
 * @Description: TODO
 * @date 2020/6/30 15:07
 */
@Service
public class ItemService implements IItemService {

    public static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemKillMapper itemKillMapper;

    /**
     * 获取待秒杀商品列表
     * @return
     * @throws Exception
     */
    @Override
    public List<ItemKill> getKillItems() throws Exception {
        return itemKillMapper.selectAll();
    }

    @Override
    public ItemKill getKillDetail(Integer id) throws Exception {
        ItemKill itemKill = itemKillMapper.selectById(id);
        if(itemKill == null){
            throw new Exception("获取待秒杀商品详情-待秒杀商品不存在");
        }
        return itemKill;
    }
}
