package com.looknlook.looknlook.shop.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.RequestDto.ReqShop;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopNo;

    private String shopNm;

    private String shopStu;

    private String shopImg;

    private String shopInfo;

    @CreationTimestamp
    private LocalDateTime regDt;

    @UpdateTimestamp
    private LocalDateTime updDt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @OneToMany(mappedBy = "shop",fetch = FetchType.LAZY)
    private List<Item> items;

    public static Shop createShop(Member member, ReqShop reqShop) {
        Shop shop = Shop.builder()
                .shopNm(reqShop.getShopNm())
                .shopImg(reqShop.getShopImg())
                .shopInfo(reqShop.getShopInfo())
                .shopStu("01002") //대기
                .member(member)
                .build();
        return shop;
    }
}
