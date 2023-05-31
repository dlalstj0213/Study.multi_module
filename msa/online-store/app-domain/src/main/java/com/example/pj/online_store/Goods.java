package com.example.pj.online_store;

import lombok.Getter;

import java.math.BigInteger;
import java.util.List;

public class Goods {

    private final GoodsId id;

    private final String modelId;

    private final String goodsName;

    private final Money price;

    private String quantity;

    private boolean isSoldOut;

    private String RegisteredDate;

//    abstract boolean isNew();

//    abstract GoodsState getState();

    private Goods(
            GoodsId id,
            String modelId,
            String goodsName,
            Money price
    ) {
        this.id = id;
        this.modelId = modelId;
        this.goodsName = goodsName;
        this.price = price;
    }

    /**
     *
     * @param id
     * @param modelId
     * @param goodsName
     * @param price
     * @return
     */
    public static Goods withId(
            Long id,
            String modelId,
            String goodsName,
            BigInteger price
    ) {
        return new Goods(
                new GoodsId(id),
                modelId,
                goodsName,
                new Money(price)
        );
    }

    public record GoodsId(Long id) {}
}