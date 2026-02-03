package com.mealgo.member.service;

import com.mealgo.common.code.MemberEnum;
import com.mealgo.member.domain.dto.RequestMember;
import com.mealgo.member.domain.dto.ResponseMember;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Integer createMember(RequestMember requestMember) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String memPw = passwordEncoder.encode(requestMember.getMemPw());

        Member member = Member.builder()
                .memId(requestMember.getMemId())
                .memPw(memPw)
                .memNm(requestMember.getMemNm())
                .memStat(MemberEnum.OK)
                .memRegDt(LocalDateTime.now())
                .memAuth("ROLE_MEMBER")
                .build();
        memberRepository.save(member);
        return member.getMemNo();
    }

    public Member readMember(Integer memNo) {
        return memberRepository.findById(memNo).orElse(null);
    }

    public Page<ResponseMember> readMemberListForPage(Pageable pageable) {
        return memberRepository.findAllPage(pageable);
    }

    public List<ResponseMember> readMemberList(){
        return memberRepository.findAllList();
    }

    public Member findById(Integer memNo) {
        return memberRepository.findById(memNo).orElse(new Member());
    }
}
