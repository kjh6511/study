package com.mealgo.category.service;

import com.mealgo.category.domain.dto.RequestCategory;
import com.mealgo.category.domain.dto.ResponseCategory;
import com.mealgo.category.domain.entity.Category;
import com.mealgo.category.repository.CategoryRepository;
import com.mealgo.utils.log.LogUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(RequestCategory requestCategory) {
        Category parent = null;
        if (requestCategory.getCateTopNo() != null) {
            parent = categoryRepository.findById(requestCategory.getCateTopNo())
                    .orElseThrow(() -> new EntityNotFoundException("상위 카테고리 없음"));
        }

        Category category = Category.builder()
                .cateNm(requestCategory.getCateNm())
                .cateLv(requestCategory.getCateLv())
                .cateStat(requestCategory.getCateStat())
                .parent(parent)
                .build();
        LogUtil.logPlain(category);
        categoryRepository.save(category);
    }

    public void updateCategory(RequestCategory requestCategory) {
        Category category = categoryRepository.findById(requestCategory.getCateNo())
                .orElseThrow(() -> new EntityNotFoundException("카테고리 없음"));

        category = Category.builder()
                .cateNo(category.getCateNo())
                .cateNm(requestCategory.getCateNm())
                .cateLv(requestCategory.getCateLv())
                .cateStat(requestCategory.getCateStat())
                .parent(requestCategory.getCateTopNo() != null ?
                        categoryRepository.findById(requestCategory.getCateTopNo()).orElse(null) : null)
                .cateRegDt(category.getCateRegDt())
                .build();
        LogUtil.logJson(category);
        categoryRepository.save(category);
    }

    public ResponseCategory readCategory(Integer cateNo) {
        return categoryRepository.findById(cateNo)
                .map(ResponseCategory::from)
                .orElseThrow(() -> new EntityNotFoundException("카테고리 없음"));
    }

    public List<ResponseCategory> readCategoryList() {
        return categoryRepository.findAll().stream()
                .map(ResponseCategory::from)
                .collect(Collectors.toList());
    }

    public List<ResponseCategory> getCategoriesByLevel(int level) {
        return categoryRepository.findByCateLv(level).stream()
                .map(ResponseCategory::from)
                .collect(Collectors.toList());
    }

    public List<ResponseCategory> getChildrenByParentNo(Integer parentNo) {
        return categoryRepository.findByCateTopNo(parentNo)
                .stream()
                .map(ResponseCategory::from)
                .toList();
    }

}
