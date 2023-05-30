package com.example.pj.online_shop.command;

import com.example.pj.online_store.Goods;

public record GetGoodsListCommand(
        Goods.GoodsId goodsId,
        String orderId,
        String sortBy
) {
}