package com.mealgo.delivery.repository;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.orders.domain.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

    @Query("SELECT d FROM Delivery d WHERE d.deliStat IN :statuses")
    List<Delivery> findByDeliStatIn(@Param("statuses") List<DeliveryEnum> statuses);

    List<Delivery> findByOrders(Orders orders);
}
