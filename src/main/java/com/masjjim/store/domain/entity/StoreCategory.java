package com.masjjim.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreCategory {

    private Integer StoCatNo;
    private Integer StoNo;
    private String StoCatName;
    private Integer StoCatNum;
    private String StoCatStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime StoCatRegDt;
}
