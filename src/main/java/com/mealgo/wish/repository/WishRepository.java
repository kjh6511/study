package com.mealgo.wish.repository;

import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.wish.domain.entity.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish,Integer> {
    Page<Wish> findByMember_MemNo(Integer memNo, Pageable pageable);

    boolean existsByMember_MemNoAndMeal_MealNo(Integer memNo, Integer mealNo);

    void deleteByWishNo(Integer wishNo);

    boolean existsByMealAndMember_MemNo(Meal m, Integer memNo);
}
