package com.mealgo.meal.domain.dto;

import com.mealgo.category.domain.dto.ResponseCategory;
import com.mealgo.common.code.MealEnum;
import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.shop.domain.dto.ResponseShop;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseMeal {

    private Integer mealNo;

    private String mealNm;

    private String mealInfo;

    private Integer mealPri;

    private MealEnum mealStat;

    private ResponseCategory category;

    private ResponseShop shop;

    private LocalDateTime mealRegDt;

    private LocalDateTime mealUpDt;

    private boolean wished;

    public static ResponseMeal from(Meal meal) {
        return ResponseMeal.builder()
                .mealNo(meal.getMealNo())
                .mealNm(meal.getMealNm())
                .mealInfo(meal.getMealInfo())
                .mealPri(meal.getMealPri())
                .mealStat(meal.getMealStat())
                .category(ResponseCategory.from(meal.getCategory()))
                .shop(ResponseShop.from(meal.getShop()))
                .mealRegDt(meal.getMealRegDt())
                .mealUpDt(meal.getMealUpDt())
                .wished(meal.isWished())
                .build();
    }
}

