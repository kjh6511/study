package com.masjjim.member.service;

import com.masjjim.member.domain.entity.Member;
import com.masjjim.member.domain.request.ReqRegister;
import com.masjjim.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public String checkMemberId(String memId) {
        String result = "SUCCESS";
        int check = memberMapper.checkMemId(memId);
        if(check >= 1){
            result = "FAILURE";
        }
        return result;
    }

    public void createMember(ReqRegister reqRegister) {
        Member member = Member.builder()
                .memId(reqRegister.getMemId())
                .memPw(reqRegister.getMemPw())
                .memName(reqRegister.getMemName())
                .memCell(reqRegister.getMemCell())
                .memNic(reqRegister.getMemNic())
                .memEmail(reqRegister.getMemEmail())
                .memType(reqRegister.getMemType())
                .memStat("001001")
                .memAuth("ROLE_MEMBER")
                .build();
        memberMapper.createMember(member);
    }

}
