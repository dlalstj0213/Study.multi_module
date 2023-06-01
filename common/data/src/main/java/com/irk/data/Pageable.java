package com.irk.data;

public interface Pageable {
    int DEFAULT_PAGE_SIZE = 100;
    int DEFAULT_PAGE_NO = 0;

    int pageNo();

    int pageSize();

    default void validPageable() {
    }
}