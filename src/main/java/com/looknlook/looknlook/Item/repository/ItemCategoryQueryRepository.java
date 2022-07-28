package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;

import java.util.List;

public interface ItemCategoryQueryRepository {
    List<ItemCategory> findAllWithTotal();

    List<ItemCategory> findAllWithDown(Long icNum);
}
