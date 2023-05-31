package com.example.pj.online_store.adapter.out.repository;

import com.example.pj.online_store.adapter.out.entity.GoodsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
class GoodsRepositoryTest {

    @Autowired
    GoodsRepository repository;

    @Test
    void createTest() {
        final String MODEL_ID = "PROD-1";
        GoodsEntity result = repository.save(new GoodsEntity(null, MODEL_ID, "상품명", new BigInteger("10000005")));
        assertNotEquals(null, result.getId());
        assertEquals(MODEL_ID, result.getModelId());
    }

    @Test
    void readTest() {
        repository.saveAll(List.of(
                new GoodsEntity(null, "model1", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model2", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model3", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model4", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model5", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model6", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model7", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model8", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model9", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model10", "상품명", new BigInteger("10000005")),
                new GoodsEntity(null, "model11", "상품명", new BigInteger("10000005"))
        ));

        List<GoodsEntity> entityList = repository.findAll(PageRequest.of(0, 10)).getContent();
        entityList.forEach(System.out::println);
        assertEquals(10, entityList.size());
    }

    @Test
    void updateTest() {
        final String MODEL_ID = "PROD-1";
        GoodsEntity createdResult = repository.save(new GoodsEntity(null, MODEL_ID, "상품명", new BigInteger("10000005")));
        createdResult.setName("상품명2");
        GoodsEntity updatedResult = repository.saveAndFlush(createdResult);

        assertEquals(createdResult.getId(), updatedResult.getId());
        assertEquals(createdResult.getName(), updatedResult.getName());
    }

    @Test
    void deleteTest() {
        final String MODEL_ID = "PROD-1";
        GoodsEntity createdResult = repository.save(new GoodsEntity(null, MODEL_ID, "상품명", new BigInteger("10000005")));
        repository.delete(createdResult);
        assertEquals(0, repository.findAll().size());
    }
}