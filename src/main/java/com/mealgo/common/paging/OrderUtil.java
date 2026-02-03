package com.mealgo.common.paging;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderUtil {

    public static <T> List<OrderSpecifier<?>> getOrderSpecifiers(Pageable pageable, Class<T> entityClass, PathBuilder<T> entityPath) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        for (Sort.Order order : pageable.getSort()) {
            String property = order.getProperty();
            boolean ascending = order.isAscending();

            OrderSpecifier<?> orderSpecifier = createOrderSpecifier(entityPath, property, ascending);
            if (orderSpecifier != null) {
                orders.add(orderSpecifier);
            }
        }

        return orders;
    }

    private static <T> OrderSpecifier<?> createOrderSpecifier(PathBuilder<T> entityPath, String property, boolean asc) {
        if (property.toLowerCase().contains("Dt")) {
            // 날짜 타입: getComparable 사용
            ComparableExpression<LocalDateTime> datePath = entityPath.getComparable(property, LocalDateTime.class);
            return asc ? datePath.asc() : datePath.desc();

        } else if (property.toLowerCase().contains("No")) {
            // 숫자 타입
            NumberExpression<Integer> numberPath = entityPath.getNumber(property, Integer.class);
            return asc ? numberPath.asc() : numberPath.desc();

        } else {
            // 기본 문자열
            StringExpression stringPath = entityPath.getString(property);
            return asc ? stringPath.asc() : stringPath.desc();
        }
    }

}
