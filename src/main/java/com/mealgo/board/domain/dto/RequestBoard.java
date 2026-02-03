package com.mealgo.board.domain.dto;

import com.mealgo.common.code.BoardTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestBoard {

    private BoardTypeEnum brdType;         // 게시판 종류 코드 (예: "10001")
    private String brdTitle;
    private String brdContent;
}
