package com.example.pj.online_store.adapter.out.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigInteger;
import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Entity(name = "goods")
@Table(name = "GOODS")
public class GoodsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String modelId;

    @Column
    private String name;

    private BigInteger price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsEntity that = (GoodsEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}