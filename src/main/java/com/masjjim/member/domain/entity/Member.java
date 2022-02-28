package com.masjjim.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Member {

    private Integer memNo;
    private String memId;
    private String memPw;
    private String memName;
    private String memNic;
    private String memCell;
    private String memType;
    private String memStat;
    private String memEmail;
    private String memAuth;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime memRegDt;
}
