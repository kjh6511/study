package com.mealgo.orders.repository;

import com.mealgo.member.domain.entity.QMember;
import com.mealgo.orders.domain.dto.SearchOrderCondition;
import com.mealgo.orders.domain.entity.Orders;
import com.mealgo.orders.domain.entity.QOrders;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

public class OrdersRepositoryImpl implements OrdersRepositoryQuerydsl{

    private final JPAQueryFactory queryFactory;

    public OrdersRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Orders> search(SearchOrderCondition condition, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        QOrders orders = QOrders.orders;
        QMember member = QMember.member;
        // 로그인한 회원 조건 추가
        if (condition.getMemNo() != null) {
            builder.and(orders.member.memNo.eq(condition.getMemNo()));
        }

        // 키워드 검색
        if (StringUtils.hasText(condition.getKeyword())) {
            builder.and(
                    orders.orderNum.containsIgnoreCase(condition.getKeyword())
                            .or(orders.member.memNm.containsIgnoreCase(condition.getKeyword()))
            );
        }

        List<Orders> content = queryFactory
                .selectFrom(orders)
                .leftJoin(orders.member, member).fetchJoin()
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orders.orderNo.desc())
                .fetch();

        long total = queryFactory
                .select(orders.count())
                .from(orders)
                .leftJoin(orders.member, member)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
