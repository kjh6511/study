package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.entity.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QItemCategory.itemCategory;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;


public class ItemQueryRepositoryImpl implements ItemQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ItemQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Item findByIdWithStock(Long itemNo) {
        return queryFactory
                .selectFrom(item)
                .leftJoin(item.stocks, stock)
                .where(item.itemNo.eq(itemNo))
                .fetchOne();
    }

    @Override
    public List<Item> findAllByShopNoWithStock(Long shopNo) {
        return queryFactory
                .select(new QItem(item))
                .from(item)
                .leftJoin(item.itemCategory,itemCategory).fetchJoin()
                .leftJoin(item.stocks, stock).fetchJoin()
                .where(item.shop.shopNo.eq(shopNo))
                .distinct()
                .fetch();
    }
}
