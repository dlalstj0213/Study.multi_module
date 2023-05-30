package com.example.pj.online_shop.query;

import com.example.pj.online_store.Goods;

public interface GetGoodsDetailQuery {

    Goods getGoodsDetail(Goods.GoodsId goodsId);
}