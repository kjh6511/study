package com.looknlook.looknlook.cart.domain.request;

import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ReqCart {

    private Long stockNo;

    private int stockQua;

}
