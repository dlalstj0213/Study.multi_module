package com.example.pj.online_shop.query;

import com.example.pj.online_shop.command.GetGoodsListCommand;
import com.example.pj.online_store.Goods;

import java.util.List;

public interface GetGoodsListQuery {

    List<Goods> getGoodsList(GetGoodsListCommand getGoodsListCommand);
}