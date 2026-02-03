package com.mealgo.cart.domain.dto;

import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.common.code.CartEnum;
import com.mealgo.meal.domain.dto.ResponseMeal;
import com.mealgo.member.domain.dto.ResponseMember;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCart {

    private Integer cartNo;
    private Integer cartCnt;
    private CartEnum cartStat;

    private ResponseMember member;
    private ResponseMeal meal;

    private LocalDateTime cartRegDt;
    private LocalDateTime cartUpDt;

    public static ResponseCart from(Cart cart) {
        return ResponseCart.builder()
                .cartNo(cart.getCartNo())
                .cartCnt(cart.getCartCnt())
                .cartStat(cart.getCartStat())
                .member(ResponseMember.from(cart.getMember()))
                .meal(ResponseMeal.from(cart.getMeal()))
                .cartRegDt(cart.getCartRegDt())
                .cartUpDt(cart.getCartUpDt())
                .build();
    }
}

