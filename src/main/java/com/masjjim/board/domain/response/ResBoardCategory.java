package com.masjjim.board.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ResBoardCategory {

    private Integer borCatNo;
    private String borCatName;
    private String borCatNum;
    private String borCatType;
    private String borCatStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime borCatRegDt;

    private List<ResBoardMenu> resBoardMenuList;

}
