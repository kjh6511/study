package com.looknlook.looknlook.bookmark.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.board.domain.entity.Board;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bmNo;

    private String bmType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_no")
    private Shop shop;
}
