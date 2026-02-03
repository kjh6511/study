package com.mealgo.delivery.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDeliveryHistory {

    private Integer dhNo;
    private String dhStat;  // 코드값 (예: "07002")
    private Integer deliNo;
}

