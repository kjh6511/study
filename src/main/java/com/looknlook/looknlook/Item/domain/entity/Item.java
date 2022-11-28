package com.looknlook.looknlook.Item.domain.entity;

import com.looknlook.looknlook.reply.domain.entity.Reply;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemNo;

    private String itemNm;

    private String itemStu;

    private String itemInfo;

    private Integer itemAmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_no")
    private Shop shop;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ic_num")
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<Reply> replies;
}
