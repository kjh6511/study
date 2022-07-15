package com.looknlook.looknlook.member.domain.RequestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReqMember {

    private String memId;

    private String memPw;

    private String memName;

    private String memType;
}
