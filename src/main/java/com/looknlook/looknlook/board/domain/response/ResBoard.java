package com.looknlook.looknlook.board.domain.response;

import com.looknlook.looknlook.board.domain.entity.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
public class ResBoard {

    private Long boardNo;

    private String boardNm;

    private String boardStu;

    private String boardType;

    private String boardText;

    private LocalDateTime boardDt;

    private LocalDateTime boardUpDt;

    private Long memNo;

    private String memName;

    private List<ResBoardItem> boardItemList;

    public ResBoard(Board board){
        this.boardNo = board.getBoardNo();
        this.boardNm = board.getBoardNm();
        this.boardStu = board.getBoardStu();
        this.boardType = board.getBoardType();
        this.boardText = board.getBoardText();
        this.boardDt = board.getBoardDt();
        this.boardUpDt = board.getBoardUpDt();
        this.memNo = board.getMember().getMemNo();
        this.memName = board.getMember().getMemName();
        this.boardItemList = board.getBoardItems()
                                    .stream().map(ResBoardItem::new)
                                    .collect(Collectors.toList());
        }

}
