package com.masjjim.board.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReqBoardMenu {

    private Integer borCateNo;
    private String borMenuName;
    private String borMenuNum;
    private String borMenuStat;

}
