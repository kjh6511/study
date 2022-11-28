package com.looknlook.looknlook.cart.repository;

import com.looknlook.looknlook.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, CartQueryRepository {


}
