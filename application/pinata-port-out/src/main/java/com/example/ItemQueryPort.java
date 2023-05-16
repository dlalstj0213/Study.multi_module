package com.example;

import java.util.List;
import java.util.Optional;

public interface ItemQueryPort <T> {
    Optional<T> loadItem(Long id);

    List<T> loadItems();
}