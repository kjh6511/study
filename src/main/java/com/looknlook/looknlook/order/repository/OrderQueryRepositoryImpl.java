package com.looknlook.looknlook.order.repository;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.order.domain.entity.Orders;
import com.looknlook.looknlook.order.domain.entity.QOrders;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.order.domain.entity.QOrderStock.orderStock;
import static com.looknlook.looknlook.order.domain.entity.QOrders.orders;

public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
