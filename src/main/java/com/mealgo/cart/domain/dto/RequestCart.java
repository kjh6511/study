package com.mealgo.cart.domain.dto;

import com.mealgo.common.code.CartEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCart {

    private Integer cartNo;
    private Integer cartCnt;
    private String cartStat;
    private Integer memNo;
    private Integer mealNo;
}

