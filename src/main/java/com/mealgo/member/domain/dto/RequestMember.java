package com.mealgo.member.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestMember {

    private Integer memNo;

    private String memId;

    private String memPw;

    private String memNm;
}
