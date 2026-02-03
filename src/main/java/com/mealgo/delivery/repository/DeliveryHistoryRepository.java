package com.mealgo.delivery.repository;

import com.mealgo.delivery.domain.entity.Delivery;
import com.mealgo.delivery.domain.entity.DeliveryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Integer> {
}
