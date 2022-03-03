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

    private Integer stoCatNo;
    private Integer stoNo;
    private String stoCatName;
    private Integer stoCatNum;
    private String stoCatStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime StoCatRegDt;
}
