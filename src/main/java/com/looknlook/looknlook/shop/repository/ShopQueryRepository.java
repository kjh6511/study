package com.looknlook.looknlook.shop.repository;

import com.looknlook.looknlook.shop.domain.entity.Shop;

public interface ShopQueryRepository {
    Shop findByMemNo(Long memNo);
}
