package com.mealgo.meal.repository;

import com.mealgo.category.domain.entity.Category;
import com.mealgo.category.domain.entity.QCategory;
import com.mealgo.category.repository.CategoryRepository;
import com.mealgo.meal.domain.dto.SearchMealCondition;
import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.meal.domain.entity.QMeal;
import com.mealgo.wish.domain.entity.QWish;
import com.mealgo.wish.repository.WishRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MealRepositoryImpl implements MealRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;
    private final CategoryRepository categoryRepository;
    private final WishRepository wishRepository;

    @Override
    public Page<Meal> search(SearchMealCondition condition, Integer memNo, Pageable pageable) {
        QMeal meal = QMeal.meal;
        QCategory category = QCategory.category;
        QWish wish = QWish.wish;

        BooleanBuilder builder = new BooleanBuilder();

        // 키워드 검색
        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(meal.mealNm.containsIgnoreCase(condition.getKeyword()));
        }

        // Lv2 직접 선택한 경우
        if (condition.getCateNo() != null) {
            builder.and(meal.category.cateNo.eq(condition.getCateNo()));
        }

        // Lv1 선택만 한 경우 → 해당 Lv1에 속한 Lv2 목록들로 in 조건
        else if (condition.getCateLv1() != null) {
            List<Integer> cateNoList = categoryRepository.findByCateTopNo(condition.getCateLv1())
                    .stream()
                    .map(Category::getCateNo)
                    .collect(Collectors.toList());

            if (!cateNoList.isEmpty()) {
                builder.and(meal.category.cateNo.in(cateNoList));
            } else {
                builder.and(meal.category.cateNo.eq(-1)); // 결과 없게 처리
            }
        }

        List<Meal> content = queryFactory
                .selectFrom(meal)
                .leftJoin(meal.category, category).fetchJoin()
                .leftJoin(meal.shop).fetchJoin()
                .leftJoin(wish).on(wish.meal.eq(meal).and(wish.member.memNo.eq(memNo)))
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(meal.mealNo.desc())
                .fetch();

        // wish 테이블과 조인했지만 fetch에서는 Meal만 가져오기 때문에
        // Meal에 직접 wished 값을 true로 세팅해줘야 함
        for (Meal m : content) {
            m.setWished(
                    wishRepository.existsByMealAndMember_MemNo(m, memNo)
            );
        }

        long total = queryFactory
                .select(meal.count())
                .from(meal)
                .leftJoin(meal.category, category)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

}

