package com.mealgo.meal.repository;

import com.mealgo.meal.domain.dto.SearchMealCondition;
import com.mealgo.meal.domain.entity.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MealRepositoryQuerydsl {
    Page<Meal> search(SearchMealCondition condition, Integer memNo,Pageable pageable);
}
