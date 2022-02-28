package com.masjjim.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreMenu {

    private Integer StoCatNo;
    private Integer StoMenuNum;
    private String StoMenuName;
    private String StoMenuInfo;
    private int StoMenuPrice;
    private String StoMenuStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime StoMenuRegDt;
}
