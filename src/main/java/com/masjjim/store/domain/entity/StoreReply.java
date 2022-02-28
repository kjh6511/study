package com.masjjim.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreReply {

    private Integer stoRlyNo;
    private Integer stoNo;
    private Integer memNo;
    private Integer reStoRlyNo;
    private String stoRlyInfo;
    private String stoRlyStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stoRlyRegDt;
}
