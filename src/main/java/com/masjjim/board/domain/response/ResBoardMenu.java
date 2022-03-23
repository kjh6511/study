package com.masjjim.board.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ResBoardMenu {

    private Integer borMenuNo;
    private Integer borCatNo;
    private String borMenuName;
    private String borMenuNum;
    private String borMenuType;
    private String borMenuStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime borMenuRegDt;

}
