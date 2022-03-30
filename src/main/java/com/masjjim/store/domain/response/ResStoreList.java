package com.masjjim.store.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ResStoreList {

    private Integer stoNo;
    private String stoName;
    private Integer stoNum;
    private String stoAddr;
    private String stoAddr1;
    private String stoAddr2;
    private String stoType;
    private String stoTypeName;
    private String stoStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stoRegDt;
    private String memNic;

}
