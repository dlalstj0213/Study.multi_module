package com.example.pj.online_store.adapter.out.persistence;

import com.example.pj.online_store.adapter.out.entity.GoodsEntity;
import com.example.pj.online_store.adapter.out.repository.GoodsRepository;
import com.example.pj.online_store.port.out.ReadGoodsDetail;
import com.example.pj.online_store.port.out.ReadGoodsList;
import com.example.pj.online_store.port.out.command.ReadGoodsListCommand;
import com.example.pj.online_store.Goods;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GoodsReadAdapter implements ReadGoodsDetail, ReadGoodsList {

    private final GoodsRepository goodsRepository;

    @Override
    public Goods read(Goods.GoodsId goodsId) {

        Optional<GoodsEntity> optional = goodsRepository.findById(goodsId.id());

        if(optional.isEmpty()) return null;
        else {
            GoodsEntity entity = optional.get();
            return Goods.withId(
                    entity.getId(),
                    entity.getModelId(),
                    entity.getModelId(),
                    entity.getPrice()
            );
        }
    }

    @Override
    public List<Goods> readList(ReadGoodsListCommand readGoodsListCommand) {
        List<GoodsEntity> goodsEntityList = goodsRepository.findAll(
                PageRequest.of(
                        readGoodsListCommand.pageNo(),
                        readGoodsListCommand.pageSize(),
                        Sort.by(Sort.Order.asc("price"), Sort.Order.desc("name"))
                )
        ).getContent();

        return goodsEntityList.stream()
                .map(item -> Goods.withId(item.getId(), item.getModelId(), item.getName(), item.getPrice()))
                .toList();
    }
}