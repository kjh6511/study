package com.masjjim.board.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReqBoardCategory {

    private String checkCategory;

    private String borCatName;
    private String borCatNum;
    private String borCatType;
    private String borCatStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime borCatRegDt;

}
