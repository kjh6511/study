package com.mealgo.meal.service;

import com.mealgo.category.domain.entity.Category;
import com.mealgo.category.repository.CategoryRepository;
import com.mealgo.common.code.MealEnum;
import com.mealgo.meal.domain.dto.RequestMeal;
import com.mealgo.meal.domain.dto.ResponseMeal;
import com.mealgo.meal.domain.dto.SearchMealCondition;
import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.meal.repository.MealRepository;
import com.mealgo.shop.domain.entity.Shop;
import com.mealgo.shop.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    public void createMeal(RequestMeal request) {
        Category category = categoryRepository.findById(request.getCateNo()).orElseThrow();
        Shop shop = shopRepository.findById(request.getShopNo()).orElseThrow();

        Meal meal = Meal.builder()
                .mealNm(request.getMealNm())
                .mealPri(request.getMealPri())
                .mealInfo(request.getMealInfo())
                .mealStat(request.getMealStat())
                .category(category)
                .shop(shop)
                .mealRegDt(LocalDateTime.now())
                .build();

        mealRepository.save(meal);
    }

    @Transactional
    public void updateMeal(RequestMeal request) {
        Meal meal = mealRepository.findById(request.getMealNo()).orElseThrow();
        meal.setMealNm(request.getMealNm());
        meal.setMealPri(request.getMealPri());
        meal.setMealInfo(request.getMealInfo());
        meal.setMealStat(request.getMealStat());
        meal.setCategory(categoryRepository.findById(request.getCateNo()).orElseThrow());
        meal.setShop(shopRepository.findById(request.getShopNo()).orElseThrow());
        meal.setMealUpDt(LocalDateTime.now());

    }

    public List<ResponseMeal> findAll() {
        return mealRepository.findAll().stream()
                .map(ResponseMeal::from)
                .toList();
    }

    public ResponseMeal findById(Integer mealNo) {
        return mealRepository.findById(mealNo)
                .map(ResponseMeal::from)
                .orElseThrow();
    }

    public Page<ResponseMeal> readMealList(SearchMealCondition condition,Integer memNo, Pageable pageable) {
        return mealRepository.search(condition, memNo, pageable)
                .map(ResponseMeal::from);
    }
}

