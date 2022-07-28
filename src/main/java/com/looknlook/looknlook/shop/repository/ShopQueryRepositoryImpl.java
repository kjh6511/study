package com.looknlook.looknlook.shop.repository;

import com.looknlook.looknlook.member.domain.entity.QMember;
import com.looknlook.looknlook.shop.domain.entity.QShop;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.member.domain.entity.QMember.member;
import static com.looknlook.looknlook.shop.domain.entity.QShop.shop;

public class ShopQueryRepositoryImpl implements ShopQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ShopQueryRepositoryImpl(EntityManager entityManager) {
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

    @Override
    public Shop findByMemNoWithMember(Long memNo) {
        return queryFactory
                .select(new QShop(shop))
                .from(shop)
                .innerJoin(shop.member, member).fetchJoin()
                .where(shop.member.memNo.eq(memNo))
                .distinct()
                .fetchOne();

    }
}
