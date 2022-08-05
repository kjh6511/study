package com.looknlook.looknlook.cart.repository;

import com.looknlook.looknlook.cart.domain.response.QResCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.looknlook.looknlook.Item.domain.entity.QItem.item;
import static com.looknlook.looknlook.Item.domain.entity.QStock.stock;
import static com.looknlook.looknlook.cart.domain.entity.QCart.cart;
import static com.looknlook.looknlook.member.domain.entity.QMember.member;


public class CartQueryRepositoryImpl implements CartQueryRepository {

    private final JPAQueryFactory queryFactory;

    public CartQueryRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public List<ResCart> findAllByMemNo(Long memNo) {
        return queryFactory
                .select(new QResCart(cart))
                .from(cart)
                .leftJoin(cart.member, member).fetchJoin()
                .leftJoin(cart.stock, stock).fetchJoin()
                .leftJoin(cart.stock.item, item).fetchJoin()
                .where(cart.member.memNo.eq(memNo))
                .distinct()
                .fetch();
    }
}
