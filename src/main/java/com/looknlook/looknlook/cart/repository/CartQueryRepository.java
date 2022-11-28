package com.looknlook.looknlook.cart.repository;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import com.looknlook.looknlook.cart.domain.response.ResCart;

import java.util.List;

public interface CartQueryRepository {
    List<ResCart> findAllByMemNo(Long memNo);

    ResCart findByCartNoWithStock(Long cartNo);
}
