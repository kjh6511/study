package com.mealgo.cart.domain.entity;

import com.mealgo.common.code.CartEnum;
import com.mealgo.common.converter.CartConverter;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.meal.domain.entity.Meal;
import com.mealgo.orders.domain.entity.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartNo;

    private Integer cartCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memNo")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mealNo")
    private Meal meal;

    @Convert(converter= CartConverter.class)
    private CartEnum cartStat;

    private LocalDateTime cartRegDt;

    private LocalDateTime cartUpDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderNo")
    private Orders orders;

    @PrePersist
    public void prePersist() {
        this.cartRegDt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.cartUpDt = LocalDateTime.now();
    }
}
