package com.example.pj.online_store;

import lombok.Getter;

import java.util.List;

public abstract class Goods {

    private GoodsId id;

    private String goodsName;

    private Money price;

    private String quantity;

    private boolean isSoldOut;

    private String RegisteredDate;

    abstract boolean isNew();

    abstract GoodsState getState();


    public record GoodsId(String id) {}
}