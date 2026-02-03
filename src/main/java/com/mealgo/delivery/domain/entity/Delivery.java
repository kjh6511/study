package com.mealgo.delivery.domain.entity;

import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.common.converter.DeliveryConverter;
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
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliNo;

    @Convert(converter = DeliveryConverter.class)
    private DeliveryEnum deliStat;  // 배송 상태 (예: 배송준비, 배송중 등)

    private LocalDateTime deliDt;  // 배송 일시

    private String deliInfo;  // 배송 정보 (ex: 택배사/송장번호 등)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderNo")
    private Orders orders;

    @PrePersist
    public void prePersist() {
        this.deliDt = LocalDateTime.now();
    }
}

