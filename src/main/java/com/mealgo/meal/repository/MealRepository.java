package com.mealgo.meal.repository;

import com.mealgo.meal.domain.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Integer>, MealRepositoryQuerydsl {
}
