package com.mealgo.meal.domain.dto;

import com.mealgo.category.domain.dto.ResponseCategory;
import com.mealgo.common.code.MealEnum;
import com.mealgo.shop.domain.dto.ResponseShop;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestMeal {

    private Integer mealNo;

    private String mealNm;

    private String mealInfo;

    private Integer mealPri;

    private MealEnum mealStat;

    private Integer cateNo;

    private Integer shopNo;

    private ResponseCategory category;//수정 변환용

    private ResponseShop shop; //수정 변환용
}

