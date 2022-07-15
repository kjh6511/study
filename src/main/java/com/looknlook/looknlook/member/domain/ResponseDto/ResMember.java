package com.looknlook.looknlook.member.domain.ResponseDto;

import com.looknlook.looknlook.member.domain.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class ResMember {

    private Long memNo;

    private String memId;

    private String memName;

    private String memType;

    private String memStu;

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
