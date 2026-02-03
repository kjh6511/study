package com.mealgo.cart.domain.dto;

import lombok.Data;

@Data
public class CartQuantityRequest {
    private Integer cartNo;
    private int delta; // +1 or -1
}
