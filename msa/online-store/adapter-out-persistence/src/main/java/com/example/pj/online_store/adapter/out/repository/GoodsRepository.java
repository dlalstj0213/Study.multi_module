package com.example.pj.online_store.adapter.out.repository;

import com.example.pj.online_store.adapter.out.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
}