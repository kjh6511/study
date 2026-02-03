package com.mealgo.delivery.domain.entity;

import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.common.converter.DeliveryConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dhNo;

    @Convert(converter = DeliveryConverter.class)
    private DeliveryEnum dhStat;  // 배송 상태

    private LocalDateTime dhRegDt;  // 등록일시

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliNo")
    private Delivery delivery;

    @PrePersist
    public void prePersist() {
        this.dhRegDt = LocalDateTime.now();
    }
}

