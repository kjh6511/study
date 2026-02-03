package com.mealgo.cart.repository;

import com.mealgo.cart.domain.entity.Cart;

import java.util.Optional;

public interface CartRepositoryQuerydsl {
    Optional<Cart> findByMemNoAndMeal(Integer mealNo, Integer memNo);
}
