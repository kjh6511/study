package com.mealgo.cart.repository;

import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.cart.domain.entity.QCart;
import com.mealgo.common.code.CartEnum;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class CartRepositoryImpl implements CartRepositoryQuerydsl{
    private final JPAQueryFactory queryFactory;

    public CartRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Cart> findByMemNoAndMeal(Integer mealNo, Integer memNo) {
        QCart cart = QCart.cart;
        return Optional.ofNullable(
                queryFactory.selectFrom(cart)
                        .where(
                                cart.meal.mealNo.eq(mealNo),
                                cart.member.memNo.eq(memNo),
                                cart.cartStat.ne(CartEnum.DELETE)
                        )
                        .fetchOne()
        );
    }

}
