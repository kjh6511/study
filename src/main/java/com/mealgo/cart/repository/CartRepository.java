package com.mealgo.cart.repository;

import com.mealgo.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer>,CartRepositoryQuerydsl {

    @Query(value = "SELECT c FROM Cart c WHERE c.member.memNo= :memNo AND c.cartStat = '05001'")
    List<Cart> findByMemNo(@Param("memNo") Integer memNo);

    List<Cart> findByCartNoIn(List<Integer> cartNos);
}
