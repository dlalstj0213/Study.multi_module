package com.example.pj.online_store.port.out;

import com.example.pj.online_store.port.out.command.ReadGoodsListCommand;
import com.example.pj.online_store.Goods;

import java.util.List;

public interface ReadGoodsList {

    List<Goods> readList(ReadGoodsListCommand readGoodsListCommand);
}