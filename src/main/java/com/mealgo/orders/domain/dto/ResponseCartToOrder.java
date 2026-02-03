package com.mealgo.orders.domain.dto;

import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.common.code.CartEnum;
import com.mealgo.meal.domain.dto.ResponseMeal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCartToOrder {

    private Integer cartNo;
    private Integer cartCnt;
    private CartEnum cartStat;

    private ResponseMeal meal;

    public static ResponseCartToOrder from(Cart cart) {
        return ResponseCartToOrder.builder()
                .cartNo(cart.getCartNo())
                .cartCnt(cart.getCartCnt())
                .cartStat(cart.getCartStat())
                .meal(ResponseMeal.from(cart.getMeal()))
                .build();
    }
}

