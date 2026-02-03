package com.mealgo.ai.domain.dto;

import lombok.Data;

@Data
public class AIMealRequest {
    private String gender;   // 성별 ("male", "female")
    private Integer age;     // 나이
    private Integer height;  // 키
    private Integer weight;  // 체중(kg)
    private String goal;     // 목표 ("lose", "maintain", "gain")
    private String allergy;  // 알러지/피할 음식 (쉼표로 구분)
    private String like;     // 좋아하는 음식 (쉼표로 구분)

    // 필요하면 키, 활동량 등도 추가 가능!
}

