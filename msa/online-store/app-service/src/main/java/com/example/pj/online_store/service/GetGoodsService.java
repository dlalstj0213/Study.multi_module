package com.example.pj.online_store.service;

import com.example.pj.online_shop.command.GetGoodsListCommand;
import com.example.pj.online_store.port.out.ReadGoodsDetail;
import com.example.pj.online_store.port.out.ReadGoodsList;
import com.example.pj.online_store.port.out.command.ReadGoodsListCommand;
import com.example.pj.online_shop.query.GetGoodsDetailQuery;
import com.example.pj.online_shop.query.GetGoodsListQuery;
import com.example.pj.online_store.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGoodsService implements GetGoodsDetailQuery, GetGoodsListQuery {

    private final ReadGoodsDetail readGoodsDetail;
    private final ReadGoodsList readGoodsList;

    @Override
    public Goods getGoodsDetail(Goods.GoodsId goodsId) {
        return readGoodsDetail.read(goodsId);
    }

    @Override
    public List<Goods> getGoodsList(GetGoodsListCommand getGoodsListCommand) {
        return readGoodsList.readList(new ReadGoodsListCommand(
                getGoodsListCommand.goodsId(),
                getGoodsListCommand.orderId(),
                getGoodsListCommand.sortBy()));
    }
}