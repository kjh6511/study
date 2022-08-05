package com.looknlook.looknlook.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class OrderQueryRepositoryImpl implements OrderQueryRepository{

    private final JPAQueryFactory queryFactory;

    public OrderQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
