package com.mealgo.board.domain.dto;

import com.mealgo.board.domain.entity.Board;
import com.mealgo.common.code.BoardEnum;
import com.mealgo.common.code.BoardTypeEnum;
import com.mealgo.common.converter.BoardConverter;
import com.mealgo.common.converter.BoardTypeConverter;
import com.mealgo.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBoard {
    private Integer brdNo;

    private BoardTypeEnum brdType;

    private BoardEnum brdStat;

    private String brdTitle;

    private String brdContent;

    private Integer memNo;

    private String memNm;

    private LocalDateTime brdRegDt;

    private LocalDateTime brdUpDt;

    public static ResponseBoard from(Board board) {
        return ResponseBoard.builder()
                .brdNo(board.getBrdNo())
                .brdType(board.getBrdType())
                .brdStat(board.getBrdStat())
                .brdTitle(board.getBrdTitle())
                .brdContent(board.getBrdContent())
                .memNo(board.getMember().getMemNo())
                .memNm(board.getMember().getMemNm())
                .brdRegDt(board.getBrdRegDt())
                .brdUpDt(board.getBrdUpDt())
                .build();
    }
}
