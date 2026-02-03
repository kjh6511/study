package com.mealgo.orders.domain.dto;

import com.mealgo.orders.domain.entity.Orders;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseOrder {

    private Integer orderNo;  // 주문 고유 번호
    private String orderNum;
    private Integer memNo;  // 회원 번호
    private String memberName;  // 회원 이름 (필요한 경우)
    private Integer orderAm;  // 주문 금액
    private String orderMet;  // 주문 방법 (예: 카드, 계좌이체 등)
    private Integer orderShip;  // 배송 금액
    private Integer orderDis;  // 할인 금액
    private String orderMetDis;  // 결제 할인 (예: 10% 할인)
    private String orderStat;  // 주문 상태 (예: 결제 완료, 취소 등)
    private LocalDateTime orderRegDt;  // 주문 등록 시간
    private LocalDateTime orderUpDt;  // 주문 업데이트 시간

    private List<ResponseCartToOrder> carts;

    // 빌더 패턴을 통해 쉽게 객체를 생성할 수 있도록
    public static ResponseOrder from(Orders orders) {
        List<ResponseCartToOrder> responseCartToOrders = orders.getCarts() != null ?
                orders.getCarts().stream()
                .map(cart -> ResponseCartToOrder.from(cart))
                .collect(Collectors.toList()) : Collections.emptyList();
        return ResponseOrder.builder()
                .orderNo(orders.getOrderNo())
                .orderNum(orders.getOrderNum())
                .memNo(orders.getMember().getMemNo())
                .memberName(orders.getMember().getMemNm())  // 회원 이름을 넣을 수도 있음 (필요 시)
                .orderAm(orders.getOrderAm())
                .orderMet(orders.getOrderMet() != null ? orders.getOrderMet().name() : null)  // 주문 방법을 Enum으로 변환
                .orderShip(orders.getOrderShip())
                .orderDis(orders.getOrderDis())
                .orderMetDis(orders.getOrderMetDis() != null ? orders.getOrderMetDis().name() : null)  // 결제 할인도 Enum으로 변환
                .orderStat(orders.getOrderStat() != null ? orders.getOrderStat().name() : null)  // 주문 상태도 Enum으로 변환
                .orderRegDt(orders.getOrderRegDt())
                .orderUpDt(orders.getOrderUpDt())
                .carts(responseCartToOrders)
                .build();
    }
}

