package com.mealgo.meal.domain.dto;

import lombok.Data;

@Data
public class SearchMealCondition {
    private String keyword;
    private Integer cateLv1; // level 1
    private Integer cateNo;
}

