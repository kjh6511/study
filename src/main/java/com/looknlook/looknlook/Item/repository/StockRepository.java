package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
}
