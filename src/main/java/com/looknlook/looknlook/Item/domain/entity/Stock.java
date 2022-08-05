package com.looknlook.looknlook.Item.domain.entity;

import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.order.domain.entity.Order;
import com.looknlook.looknlook.order.domain.entity.OrderStock;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockNo;

    private String stockNm;

    private String stockStu;

    private String stockSize;

    private Integer stockQtq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
    private List<OrderStock> orderStocks;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
    private List<Cart> carts;
}
