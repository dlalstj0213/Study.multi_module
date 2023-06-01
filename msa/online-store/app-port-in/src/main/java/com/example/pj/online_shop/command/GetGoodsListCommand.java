package com.example.pj.online_shop.command;

public record GetGoodsListCommand(String search, int pageNo, int pageSize, String orderBy, String sortDirection) {}