package com.mealgo.shop.repository;

import com.mealgo.common.paging.OrderUtil;
import com.mealgo.common.paging.SearchCondition;
import com.mealgo.shop.domain.entity.QShop;
import com.mealgo.shop.domain.entity.Shop;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

public class ShopRepositoryImpl implements ShopRepositoryQuerydsl {

    private final JPAQueryFactory queryFactory;

    public ShopRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Shop> search(SearchCondition condition, Pageable pageable) {
        QShop shop = QShop.shop;

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(shop.shopNm.contains(condition.getKeyword()));
        }

        List<OrderSpecifier<?>> orders = OrderUtil.getOrderSpecifiers(
                pageable,
                Shop.class,
                new PathBuilder<>(Shop.class, "shop")
        );


        List<Shop> content = queryFactory
                .selectFrom(shop)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .fetch();

        long total = queryFactory
                .select(shop.count())
                .from(shop)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
