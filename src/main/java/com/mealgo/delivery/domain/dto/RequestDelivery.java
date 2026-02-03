package com.mealgo.delivery.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDelivery {

    private Integer deliNo;
    private String deliStat;  // 코드값으로 받을 경우
    private String deliInfo;
    private Integer cartNo;
}

