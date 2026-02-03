package com.mealgo.category.domain.dto;

import com.mealgo.common.code.CategoryEnum;
import com.mealgo.category.domain.entity.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCategory {

    private Integer cateNo;
    private String cateNm;
    private Integer cateLv;
    private CategoryEnum cateStat;
    private Integer cateTopNo;
    private String cateTopNm;
    private LocalDateTime cateRegDt;
    private LocalDateTime cateUpDt;

    private List<ResponseCategory> children; // 계층형 응답용

    public static ResponseCategory from(Category category) {
        return ResponseCategory.builder()
                .cateNo(category.getCateNo())
                .cateNm(category.getCateNm())
                .cateLv(category.getCateLv())
                .cateStat(category.getCateStat())
                .cateTopNo(category.getParent() != null ? category.getParent().getCateNo() : null)
                .cateTopNm(category.getParent() != null ? category.getParent().getCateNm() : null)
                .cateRegDt(category.getCateRegDt())
                .cateUpDt(category.getCateUpDt())
                .children(
                        category.getChildren() != null ?
                                category.getChildren().stream()
                                        .map(ResponseCategory::from)
                                        .collect(Collectors.toList()) : null
                )
                .build();
    }
}
