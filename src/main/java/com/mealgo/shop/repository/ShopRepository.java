package com.mealgo.shop.repository;

import com.mealgo.shop.domain.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer>, ShopRepositoryQuerydsl {

}
