package com.irk.data;

import lombok.NonNull;

record PageableSearch(String searchText,
                      int pageNo,
                      int pageSize,
                      String orderBy,
                      String sortDirection) implements Pageable {

    public PageableSearch {
        pageNo = pageNo == 0 ? 0 : pageNo - 1;
        if (pageNo < 0) throw new IllegalArgumentException("Wrong argument of pageNo");
        if (pageSize < 0) throw new IllegalArgumentException("Wrong argument of pageSize");
    }

    public PageableSearch(String searchText, String orderBy, String sortDirection) {
        this(searchText, DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE, orderBy, sortDirection);
    }

    @Override
    public int pageSize() {
        return this.pageSize;
    }

    @Override
    public int pageNo() {
        return this.pageNo;
    }
}