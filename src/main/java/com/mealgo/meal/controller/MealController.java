package com.mealgo.meal.controller;

import com.mealgo.category.domain.dto.ResponseCategory;
import com.mealgo.category.service.CategoryService;
import com.mealgo.common.code.MealEnum;
import com.mealgo.meal.domain.dto.RequestMeal;
import com.mealgo.meal.domain.dto.ResponseMeal;
import com.mealgo.meal.domain.dto.SearchMealCondition;
import com.mealgo.meal.service.MealService;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.shop.service.ShopService;
import com.mealgo.utils.log.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;
    private final CategoryService categoryService;
    private final ShopService shopService;

    @GetMapping
    public String readMealList(
            @AuthenticationPrincipal Member auth,
            @ModelAttribute SearchMealCondition condition,
            @PageableDefault(size = 10) Pageable pageable,
            Model model
    ) {
        Page<ResponseMeal> mealPage = mealService.readMealList(condition, auth.getMemNo(),pageable);

        List<ResponseCategory> lv1Categories = categoryService.getCategoriesByLevel(1);
        List<ResponseCategory> lv2Categories = (condition.getCateLv1() != null)
                ? categoryService.getChildrenByParentNo(condition.getCateLv1())
                : Collections.emptyList();

        model.addAttribute("mealPage", mealPage);
        model.addAttribute("condition", condition);
        model.addAttribute("lv1Categories", lv1Categories);
        model.addAttribute("lv2Categories", lv2Categories);

        return "meal/meal";
    }


    @GetMapping("/{mealNo}")
    public String detail(@PathVariable("mealNo") Integer mealNo, Model model) {
        model.addAttribute("meal", mealService.findById(mealNo));
        return "meal/mealDetail";
    }

    @PostMapping("/new")
    public String create(RequestMeal request) {
        LogUtil.logJson(request);
        mealService.createMeal(request);
        return "redirect:/meals";
    }

    @PostMapping("/edit")
    public String update(RequestMeal request) {
        mealService.updateMeal(request);
        return "redirect:/meals/" + request.getMealNo();
    }

    @GetMapping("/form")
    public String mealForm(@RequestParam(value = "mealNo", required = false) Integer mealNo, Model model) {
        RequestMeal meal = new RequestMeal();
        if(mealNo != null) {
            ResponseMeal responseMeal = mealService.findById(mealNo);
            meal = RequestMeal.builder()
                    .mealNo(responseMeal.getMealNo())
                    .mealNm(responseMeal.getMealNm())
                    .mealInfo(responseMeal.getMealInfo())
                    .mealPri(responseMeal.getMealPri())
                    .mealStat(responseMeal.getMealStat())
                    .cateNo(responseMeal.getCategory().getCateNo())
                    .category(responseMeal.getCategory())
                    .shopNo(responseMeal.getShop().getShopNo())
                    .shop(responseMeal.getShop())
                    .build();
        }
        model.addAttribute("meal", meal);
        model.addAttribute("mealStatusList", MealEnum.values());
        model.addAttribute("lv1Categories", categoryService.getCategoriesByLevel(1));

        Integer lv1 = meal.getCategory() != null ? meal.getCategory().getCateTopNo() : null;
        List<ResponseCategory> lv2 = lv1 != null ? categoryService.getChildrenByParentNo(lv1) : Collections.emptyList();
        model.addAttribute("lv2Categories", lv2);
        model.addAttribute("shopList", shopService.readShopAll());
        return "meal/mealForm";
    }
}

