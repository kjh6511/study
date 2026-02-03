package com.mealgo.orders.domain.entity;

import com.mealgo.cart.domain.entity.Cart;
import com.mealgo.common.code.OrderEnum;
import com.mealgo.common.code.PayEnum;
import com.mealgo.common.code.SaleEnum;
import com.mealgo.common.converter.OrderConverter;
import com.mealgo.common.converter.PayConverter;
import com.mealgo.common.converter.SaleConverter;
import com.mealgo.member.domain.entity.Member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNo;

    @Column(unique = true)
    private String orderNum; //주문번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memNo")
    private Member member;  // 회원 (Foreign key)

    private Integer orderAm;  // 주문 금액

    @Convert(converter = PayConverter.class)
    private PayEnum orderMet;  // 주문 방법 (외부 코드 참조)

    private Integer orderShip;  // 배송금액

    private Integer orderDis;  // 할인 금액

    @Convert(converter = SaleConverter.class)
    private SaleEnum orderMetDis;  // 결제 할인 (외부 코드 참조)

    @Convert(converter = OrderConverter.class)
    private OrderEnum orderStat;  // 주문 상태 (예: 결제 완료, 취소 등)

    private LocalDateTime orderRegDt;  // 주문 등록 시간

    private LocalDateTime orderUpDt;  // 주문 업데이트 시간

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>(); //구매한 장바구니

    @PrePersist
    public void prePersist() {
        this.orderRegDt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.orderUpDt = LocalDateTime.now();
    }
}

