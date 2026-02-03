package com.mealgo.category.repository;

import com.mealgo.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> findByCateLv(int cateLv);

    @Query(value = "SELECT * FROM category WHERE cateTopNo = :cateTopNo", nativeQuery = true)
    List<Category> findByCateTopNo(@Param("cateTopNo") Integer cateTopNo);

}
