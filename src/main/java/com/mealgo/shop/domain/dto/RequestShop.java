package com.mealgo.shop.domain.dto;

import com.mealgo.common.code.ShopEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestShop {

    private Integer shopNo;

    private String shopNm;

    private String shopId;

    private String shopInfo;

    private String shopTel;

    private String shopImg;

    private ShopEnum shopStat;
}
