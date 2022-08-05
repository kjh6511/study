package com.looknlook.looknlook.order.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.entity.Shop;
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
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    private LocalDateTime orderDt;

    private String orderStu;

    private LocalDateTime orderArrDt;

    private String orderPay;

    private String orderAddr;

    private String orderName;

    private String orderPhone;

    private String orderAddrNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderStock> orderStocks;
}
