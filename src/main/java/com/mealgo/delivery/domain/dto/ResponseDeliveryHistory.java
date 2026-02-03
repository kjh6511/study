package com.mealgo.delivery.domain.dto;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.delivery.domain.entity.DeliveryHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDeliveryHistory {

    private Integer dhNo;
    private DeliveryEnum dhStat;
    private LocalDateTime dhRegDt;

    public static ResponseDeliveryHistory from(DeliveryHistory history) {
        return ResponseDeliveryHistory.builder()
                .dhNo(history.getDhNo())
                .dhStat(history.getDhStat())
                .dhRegDt(history.getDhRegDt())
                .build();
    }
}

