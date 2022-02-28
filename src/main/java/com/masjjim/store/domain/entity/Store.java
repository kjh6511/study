package com.masjjim.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Store {

    private Integer StoNo;
    private String StoName;
    private Integer StoNum;
    private String stoCell;
    private String stoAddr1;
    private String stoAddr2;
    private String stoInfo;
    private String stoType;
    private String stoStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stoRegDt;
}
