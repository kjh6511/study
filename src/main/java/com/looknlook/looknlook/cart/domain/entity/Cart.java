package com.looknlook.looknlook.cart.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.order.domain.entity.OrderStock;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNo;

    private int stockQua;

    private LocalDateTime cartDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_no")
    private Stock stock;
}
