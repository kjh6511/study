package com.masjjim.member.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReqRegister {

    private String memId;
    private String memPw;
    private String memName;
    private String memCell;
    private String memNic;
    private String memType;
    private String memEmail;

}
