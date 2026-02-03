package com.mealgo.category.domain.dto;

import com.mealgo.common.code.CategoryEnum;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestCategory {

    private Integer cateNo;// 수정 시 사용
    private String cateNm;
    private Integer cateLv;
    private CategoryEnum cateStat;
    private Integer cateTopNo;// 상위 카테고리 번호 (nullable)
}
