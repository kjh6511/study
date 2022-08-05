package com.looknlook.looknlook.order.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.member.domain.entity.Member;
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
@Table(name = "order_stock")
public class OrderStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderStockNo;

    private String orderStockStu;

    private LocalDateTime orderStockDt;

    private int orderStockQua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_no")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_no")
    private Stock stock;
}
