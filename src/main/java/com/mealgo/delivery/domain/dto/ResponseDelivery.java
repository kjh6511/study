package com.mealgo.delivery.domain.dto;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.orders.domain.dto.ResponseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDelivery {

    private Integer deliNo;
    private DeliveryEnum deliStat;
    private String deliInfo;
    private LocalDateTime deliDt;
    private ResponseOrder order;

    public static ResponseDelivery from(Delivery delivery) {
        return ResponseDelivery.builder()
                .deliNo(delivery.getDeliNo())
                .deliStat(delivery.getDeliStat())
                .deliInfo(delivery.getDeliInfo())
                .deliDt(delivery.getDeliDt())
                .order(ResponseOrder.from(delivery.getOrders()))
                .build();
    }
}


