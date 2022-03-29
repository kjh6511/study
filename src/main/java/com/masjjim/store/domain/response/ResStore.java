package com.masjjim.store.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ResStore {

    private Integer stoNo;
    private String stoName;
    private Integer stoNum;
    private String stoCell;
    private String stoAddr;
    private String stoAddr1;
    private String stoAddr2;
    private String stoInfo;
    private String stoType;
    private String stoTypeName;
    private String stoStat;
    private String stoStatName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stoRegDt;



}
