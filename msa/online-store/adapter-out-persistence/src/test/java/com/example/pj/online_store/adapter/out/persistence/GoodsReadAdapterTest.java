package com.example.pj.online_store.adapter.out.persistence;

import com.example.pj.online_store.port.out.ReadGoodsDetail;
import com.example.pj.online_store.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(GoodsReadAdapter.class)
class GoodsReadAdapterTest {

    @Autowired
    ReadGoodsDetail readGoodsDetail;

    @Test
    void readTest() {
        Goods goods = readGoodsDetail.read(new Goods.GoodsId(1L));
        System.out.println(goods);
    }

    @Test
    void readListTest() {

    }
}