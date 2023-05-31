package com.example.pj.online_store.port.out.command;

import com.example.pj.online_store.Goods;

public record ReadGoodsListCommand(
        int pageNo,
        int pageSize,
        String orderId,
        String sortId
) {
}