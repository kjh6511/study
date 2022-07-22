package com.looknlook.looknlook.shop.domain.entity;

import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.RequestDto.ReqShop;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
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

    @OneToOne
    @JoinColumn(name = "mem_no")
    private Member member;

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
