package com.mealgo.category.controller;

import com.mealgo.category.domain.dto.RequestCategory;
import com.mealgo.category.domain.dto.ResponseCategory;
import com.mealgo.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        List<ResponseCategory> categories = categoryService.readCategoryList();
        model.addAttribute("categories", categories);
        return "category/categoryList";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("category", new RequestCategory());
        model.addAttribute("lv1Categories", categoryService.getCategoriesByLevel(1));
        return "category/categoryForm";
    }

    @PostMapping
    public String create(RequestCategory requestCategory) {
        categoryService.createCategory(requestCategory);
        return "redirect:/categories";
    }

    @GetMapping("/{cateNo}")
    public String detail(@PathVariable("cateNo") Integer cateNo, Model model) {
        ResponseCategory category = categoryService.readCategory(cateNo);
        model.addAttribute("category", category);
        return "category/categoryDetail";
    }

    @GetMapping("/{cateNo}/edit")
    public String editForm(@PathVariable("cateNo") Integer cateNo, Model model) {
        ResponseCategory category = categoryService.readCategory(cateNo);
        model.addAttribute("category", category);
        model.addAttribute("lv1Categories", categoryService.getCategoriesByLevel(1));
        return "category/categoryForm";
    }

    @PostMapping("/edit")
    public String update(RequestCategory requestCategory) {
        categoryService.updateCategory(requestCategory);
        return "redirect:/categories/" + requestCategory.getCateNo();
    }

    @GetMapping("/children")
    @ResponseBody
    public List<ResponseCategory> getSecondLevelCategories(@RequestParam("parentNo") Integer parentNo) {
        return categoryService.getChildrenByParentNo(parentNo);
    }
}
