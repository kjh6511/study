package com.looknlook.looknlook.reply.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Item;
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
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNo;

    private String replyStu;

    private String replyType;

    private String replyText;

    private LocalDateTime replyDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_stock_no")
    private OrderStock orderStock;

}
