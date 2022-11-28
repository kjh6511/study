package com.looknlook.looknlook.member.service;

import com.looknlook.looknlook.member.domain.RequestDto.ReqMember;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void createMember() {
        ReqMember reqMember = new ReqMember();
        reqMember.setMemId("test04");
        reqMember.setMemName("test04");
        reqMember.setMemPw("test04");
        reqMember.setMemType("02001");

        Long memNo = memberService.createMember(reqMember);
        System.out.println("memNo : "+memNo);

        Member member = memberService.readMember(memNo);
        ResMember resMember = new ResMember(member);

        System.out.println(resMember);
    }

    @Test
    void readMemberList() {
    }

    @Test
    void readMember() {
        Member member = memberService.readMember(8L);
        ResMember resMember = new ResMember(member);

        System.out.println(resMember);
    }
}