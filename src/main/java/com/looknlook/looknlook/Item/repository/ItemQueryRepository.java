package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;

import java.util.List;

public interface ItemQueryRepository {
    Item findByIdWithStock(Long itemNo);

    List<Item> findAllByShopNoWithStock(Long shopNo);
}
