package com.looknlook.looknlook.order.repository;

import com.looknlook.looknlook.order.domain.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>, OrderQueryRepository {

    @Query("select o " +
            "from Orders o " +
            "join fetch o.orderStocks os "+
            "join fetch os.stock s "+
            "join fetch s.item i "+
            "where o.orderNo = :orderNo")
    Orders findByOrderNo(@Param("orderNo") Long orderNo);
}
