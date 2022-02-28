package com.masjjim.member.domain.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResMember {

    private Integer memNo;
    private String memId;
    private String memPw;
    private String memName;
    private String memNic;
    private String memType;
    private String memStat;
    private String memEmail;
    private String memAuth;

}
