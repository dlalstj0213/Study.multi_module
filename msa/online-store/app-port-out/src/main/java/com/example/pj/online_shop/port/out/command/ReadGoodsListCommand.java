package com.example.pj.online_shop.port.out.command;

import com.example.pj.online_store.Goods;

public record ReadGoodsListCommand(
        Goods.GoodsId goodsId,
        String orderId,
        String sortId
) {
}