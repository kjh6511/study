package com.mealgo.shop.repository;

import com.mealgo.common.paging.SearchCondition;
import com.mealgo.shop.domain.entity.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopRepositoryQuerydsl{

    Page<Shop> search(SearchCondition condition, Pageable pageable);

}
