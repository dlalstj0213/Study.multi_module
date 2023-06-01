package com.example.pj.online_store.adapter.out.persistence;

import com.example.pj.online_store.Goods;
import com.example.pj.online_store.Money;
import com.example.pj.online_store.adapter.out.entity.GoodsEntity;
import com.example.pj.online_store.adapter.out.repository.GoodsRepository;
import com.example.pj.online_store.port.out.command.ReadGoodsListCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(GoodsReadAdapter.class)
class GoodsReadAdapterTest {

    @Autowired
    GoodsReadAdapter adapter;
    @Autowired
    GoodsRepository repository;

    @BeforeEach
    public void before() {
        repository.saveAll(List.of(new GoodsEntity(null, "model1", "상품명", new BigInteger("10001")),
                                   new GoodsEntity(null, "model2", "상품명", new BigInteger("10002")),
                                   new GoodsEntity(null, "model3", "상품명", new BigInteger("10003")),
                                   new GoodsEntity(null, "model4", "상품명", new BigInteger("10004")),
                                   new GoodsEntity(null, "model5", "상품명", new BigInteger("10005")),
                                   new GoodsEntity(null, "model6", "상품명", new BigInteger("10006")),
                                   new GoodsEntity(null, "model7", "상품명", new BigInteger("10007")),
                                   new GoodsEntity(null, "model8", "상품명", new BigInteger("10008")),
                                   new GoodsEntity(null, "model9", "상품명", new BigInteger("10009")),
                                   new GoodsEntity(null, "model10", "상품명", new BigInteger("10010")),
                                   new GoodsEntity(null, "model11", "상품명", new BigInteger("10011"))));
    }

    @Test
    void readTest() {
        Goods goods = adapter.read(new Goods.GoodsId(1L));
        assertNotNull(goods);
        assertEquals("model1", goods.getModelId());
    }

    @Test
    void readListTest() {
        List<Goods> resultList = adapter.readList(new ReadGoodsListCommand(0, 5, "price", "DESC"));
        resultList.forEach(System.out::println);
        assertEquals(5, resultList.size());
        assertEquals(new Money(new BigInteger("10011")), resultList.get(0).getPrice());
        assertEquals(new Money(new BigInteger("10007")), resultList.get(4).getPrice());
    }
}