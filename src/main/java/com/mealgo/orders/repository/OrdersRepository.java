package com.mealgo.orders.repository;

import com.mealgo.common.code.OrderEnum;
import com.mealgo.orders.domain.entity.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Integer>, OrdersRepositoryQuerydsl {

    @Query("SELECT COUNT(o) FROM Orders o WHERE DATE(o.orderRegDt) = :date")
    int countByDate(@Param("date") LocalDate date);

    @EntityGraph(attributePaths = "carts")
    Optional<Orders> findWithCartsByOrderNo(Integer orderNo);

    List<Orders> findByOrderStatAndOrderRegDtBefore(OrderEnum wait, LocalDateTime minusMinutes);
}
