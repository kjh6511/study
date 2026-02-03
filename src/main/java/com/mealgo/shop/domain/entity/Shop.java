package com.mealgo.shop.domain.entity;

import com.mealgo.common.code.MemberEnum;
import com.mealgo.common.code.ShopEnum;
import com.mealgo.common.converter.MemberConverter;
import com.mealgo.common.converter.ShopConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopNo;

    private String shopNm;

    private String shopId;

    private String shopInfo;

    private String shopTel;

    private String shopImg;

    @Convert(converter = ShopConverter.class)
    private ShopEnum shopStat;

    private LocalDateTime shopRegDt;

    private LocalDateTime shopUpDt;

}
