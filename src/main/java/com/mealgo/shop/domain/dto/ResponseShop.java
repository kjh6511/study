package com.mealgo.shop.domain.dto;

import com.mealgo.common.code.ShopEnum;
import com.mealgo.shop.domain.entity.Shop;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class ResponseShop {

    private Integer shopNo;

    private String shopNm;

    private String shopId;

    private String shopInfo;

    private String shopTel;

    private String shopImg;

    private ShopEnum ShopStat;

    private LocalDateTime shopRegDt;

    private LocalDateTime shopUpDt;

    public static ResponseShop from(Shop shop) {
        return new ResponseShop(
                shop.getShopNo(),
                shop.getShopNm(),
                shop.getShopId(),
                shop.getShopInfo(),
                shop.getShopTel(),
                shop.getShopImg(),
                shop.getShopStat(),
                shop.getShopRegDt(),
                shop.getShopUpDt()
        );
    }

}
