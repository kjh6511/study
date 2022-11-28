package com.looknlook.looknlook.board.domain.response;

import com.looknlook.looknlook.board.domain.entity.Board;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
public class ResBoardList {

    private Long boardNo;

    private String boardNm;

    private String boardType;

    private LocalDateTime boardDt;

    private LocalDateTime boardUpDt;

    private String memName;

    private List<ResBoardItem> boardItemList;

    @QueryProjection
    public ResBoardList(Board board) {
        this.boardNo = board.getBoardNo();
        this.boardNm = board.getBoardNm();
        this.boardType = board.getBoardType();
        this.boardDt = board.getBoardDt();
        this.boardUpDt = board.getBoardUpDt();
        this.memName = board.getMember().getMemName();
        this.boardItemList = board.getBoardItems()
                .stream().map(ResBoardItem::new)
                .collect(Collectors.toList());
    }

}
