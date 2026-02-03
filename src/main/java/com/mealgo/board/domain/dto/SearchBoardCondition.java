package com.mealgo.board.domain.dto;

import com.mealgo.common.code.BoardTypeEnum;
import lombok.Data;

@Data
public class SearchBoardCondition {
    private Integer memNo;
    private String keyword;
    private BoardTypeEnum brdType;
}
