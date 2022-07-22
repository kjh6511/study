package com.looknlook.looknlook.member.service;

import com.looknlook.looknlook.common.codeEnum.CodeStatus;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.member.domain.RequestDto.ReqMember;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(ReqMember reqMember) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String memPw = passwordEncoder.encode(reqMember.getMemPw());

        Member member = Member.builder()
                .memId(reqMember.getMemId())
                .memPw(memPw)
                .memName(reqMember.getMemName())
                .memType(reqMember.getMemType())
                .memStu(CodeStatus.OK)
                .regDt(LocalDateTime.now())
                .auth("ROLE_ADMIN")
                .build();
        memberRepository.save(member);
    }

    public List<ResMember> readMemberList() {
       return memberRepository.findAllMemberBy();
    }

    public Member readMember(Long memNo) {
        return memberRepository.findById(memNo).orElse(null);
    }

    public void updateMember(ReqMember reqMember) {
        Optional<Member> opt = memberRepository.findById(reqMember.getMemNo());
        Member member = opt.get();
        member.setMemId(reqMember.getMemId());
        member.setMemPw(reqMember.getMemPw());
        member.setMemName(reqMember.getMemName());
        memberRepository.save(member);
    }

    public String checkMemId(String memId){
        Optional<Member> member = memberRepository.findByMemId(memId);
        String result = "사용가능";
        if(member.isPresent()){
            result ="ID 중복";
        }
        return result;
    }
}
