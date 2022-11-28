package com.looknlook.looknlook.order.repository;

import com.looknlook.looknlook.order.domain.entity.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStockRepository extends JpaRepository<OrderStock, Long> {

    @Query("SELECT o FROM OrderStock o WHERE o.orderStockNo = :orderStockNo")
    OrderStock findByOrderStockNo(@Param("orderStockNo")Long orderStockNo);
}
