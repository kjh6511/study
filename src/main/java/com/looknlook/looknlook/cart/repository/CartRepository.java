package com.looknlook.looknlook.cart.repository;

import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.order.domain.entity.Order;
import com.looknlook.looknlook.order.repository.OrderQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, CartQueryRepository {


}
