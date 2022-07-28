package com.looknlook.looknlook.shop.domain.ResponseDto;

import com.looknlook.looknlook.Item.domain.response.ResItem;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ResShop {

    private Long shopNo;

    private String shopNm;

    private String shopStu;

    private String shopImg;

    private String shopInfo;

    private LocalDateTime regDt;

    private LocalDateTime updDt;

    private ResMember member;

    private List<ResItem> items;

    public ResShop(Shop shop) {
        this.shopNo = shop.getShopNo();
        this.shopNm = shop.getShopNm();
        this.shopStu = shop.getShopStu();
        this.shopImg = shop.getShopImg();
        this.shopInfo = shop.getShopInfo();
        this.regDt = shop.getRegDt();
        this.updDt = shop.getUpdDt();
        this.member = new ResMember(shop.getMember());
    }
}
