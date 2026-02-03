package com.mealgo.meal.domain.entity;

import com.mealgo.category.domain.entity.Category;
import com.mealgo.common.code.MealEnum;
import com.mealgo.common.converter.MealConverter;
import com.mealgo.shop.domain.entity.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealNo;

    private String mealNm;

    private String mealInfo;

    private Integer mealPri;

    @Convert(converter = MealConverter.class)
    private MealEnum mealStat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cateNo")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopNo")
    private Shop shop;

    private LocalDateTime mealRegDt;

    private LocalDateTime mealUpDt;

    @Transient//db저장하지 않고 표시용
    private boolean wished;

    public boolean isWished() {
        return wished;
    }

    public void setWished(boolean wished) {
        this.wished = wished;
    }

    @PrePersist
    public void prePersist() {
        this.mealRegDt = LocalDateTime.now();
        this.mealUpDt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.mealUpDt = LocalDateTime.now();
    }
}

