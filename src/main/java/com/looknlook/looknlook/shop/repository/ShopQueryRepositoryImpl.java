package com.looknlook.looknlook.shop.repository;

import com.looknlook.looknlook.shop.domain.entity.QShop;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.looknlook.looknlook.shop.domain.entity.QShop.shop;

public class ShopQueryRepositoryImpl implements  ShopQueryRepository{

    private final JPAQueryFactory queryFactory;
    public ShopQueryRepositoryImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Shop findByMemNo(Long memNo) {

        return queryFactory
                .select(new QShop(shop))
                .from(shop)
                .where(shop.member.memNo.eq(memNo))
                .fetchOne();
    }
}
