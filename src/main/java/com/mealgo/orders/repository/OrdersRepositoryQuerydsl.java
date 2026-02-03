package com.mealgo.orders.repository;

import com.mealgo.orders.domain.dto.SearchOrderCondition;
import com.mealgo.orders.domain.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersRepositoryQuerydsl {
    Page<Orders> search (SearchOrderCondition condition, Pageable pageable);
}
