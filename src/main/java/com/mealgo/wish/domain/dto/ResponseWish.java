package com.mealgo.wish.domain.dto;

import com.mealgo.meal.domain.dto.ResponseMeal;
import com.mealgo.wish.domain.entity.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseWish {
    private Integer wishNo;
    private Integer memNo;
    private Integer mealNo;
    private String mealNm;
    private String mealInfo;
    private Integer mealPri;
    private String shopNm;
    private LocalDateTime wishRegDt;

    public static ResponseWish from(Wish wish) {
        return ResponseWish.builder()
                .wishNo(wish.getWishNo())
                .memNo(wish.getMember().getMemNo())
                .mealNo(wish.getMeal().getMealNo())
                .mealNm(wish.getMeal().getMealNm())
                .mealInfo(wish.getMeal().getMealInfo())
                .mealPri(wish.getMeal().getMealPri())
                .shopNm(wish.getMeal().getShop().getShopNm())
                .wishRegDt(wish.getWishRegDt())
                .build();
    }
}
