package com.mealgo.member.service;

import com.mealgo.member.domain.dto.RequestMember;
import com.mealgo.member.domain.dto.ResponseMember;
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
        for (int i = 0; i <= 30; i++){
            String num = String.format("%02d",i);
            RequestMember requestMember = new RequestMember();
            requestMember.setMemId("mem"+num);
            requestMember.setMemNm("회원"+num);
            requestMember.setMemPw("mem"+num);
            memberService.createMember(requestMember);
        }
    }

    @Test
    void readMemberList(){
        System.out.println(memberService.readMemberList());
    }

}