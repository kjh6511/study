package com.looknlook.looknlook.order.repository;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.repository.ItemQueryRepository;
import com.looknlook.looknlook.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderQueryRepository {


}
