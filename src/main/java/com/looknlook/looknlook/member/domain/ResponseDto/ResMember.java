package com.looknlook.looknlook.member.domain.ResponseDto;

import com.looknlook.looknlook.common.codeEnum.CodeStatus;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ResMember {

    private Long memNo;

    private String memId;

    private String memName;

    private String memType;

    private CodeStatus memStu;

    private LocalDateTime regDt;

    private String auth;

    @QueryProjection
    public ResMember(Member member) {
        memNo = member.getMemNo();
        memId = member.getMemId();
        memName = member.getMemName();
        memType = member.getMemType();
        memStu = member.getMemStu();
        regDt = member.getRegDt();
        auth = member.getAuth();
    }
}
