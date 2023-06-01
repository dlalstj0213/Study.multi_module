package com.example.pj.online_store.port.out.command;

public record ReadGoodsListCommand(int pageNo, int pageSize, String orderBy, String sortDirection) {}