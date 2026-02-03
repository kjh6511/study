package com.mealgo.member.domain.dto;

import com.mealgo.common.code.MemberEnum;
import com.mealgo.member.domain.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor  //기본생성자
@AllArgsConstructor //모든생성자
public class ResponseMember {

    private Integer memNo;

    private String memId;

    private String memNm;

    private MemberEnum memStat;

    private LocalDateTime memRegDt;

    private LocalDateTime memUpDt;

    private String memAuth;

    public static ResponseMember from(Member member) {
        if (member == null) return null;

        return ResponseMember.builder()
                .memNo(member.getMemNo())
                .memId(member.getMemId())
                .memNm(member.getMemNm())
                .memStat(member.getMemStat())
                .memRegDt(member.getMemRegDt())
                .memUpDt(member.getMemUpDt())
                .memAuth(member.getMemAuth())
                .build();
    }
}
