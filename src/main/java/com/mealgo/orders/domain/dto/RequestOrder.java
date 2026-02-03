package com.mealgo.orders.domain.dto;

import com.mealgo.common.code.PayEnum;
import com.mealgo.common.code.SaleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RequestOrder {

    private Integer orderAm;  // 주문 금액

    private PayEnum orderMet;  // 주문 방법

    private Integer orderShip;  // 배송금액

    private Integer orderDis;  // 할인 금액

    private SaleEnum orderMetDis;  // 결제 할인 방법

    private List<Integer> cartList = new ArrayList<>(); //구매한 장바구니
}
