package com.looknlook.looknlook.board.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ReqBoardUpdate {

    private Long boardNo;

    private String boardNm;

    private String boardStu;

    private String boardType;

    private String boardText;

    private Long[] itemNoList;

}
