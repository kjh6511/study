package com.looknlook.looknlook.order.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.reply.domain.entity.Reply;
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
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_no")
    private Stock stock;

}
