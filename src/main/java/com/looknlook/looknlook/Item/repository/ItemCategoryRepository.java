package com.looknlook.looknlook.Item.repository;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long>, ItemCategoryQueryRepository{

    @Query(value = "SELECT * FROM item_category ic WHERE ic.ic_top_num IS NULL", nativeQuery = true)
    List<ItemCategory> findAllWithTop();
}
