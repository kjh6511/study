package com.looknlook.looknlook.member.controller;

import com.looknlook.looknlook.member.domain.RequestDto.ReqMember;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<List<ResMember>> readMemberList() throws Exception {
        List<ResMember> memberList = memberService.readMemberList();
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/myinfo")
    public String readMyinfo(@AuthenticationPrincipal Member getMember, Model model) throws Exception {
        Member member = memberService.readMember(getMember.getMemNo());
        model.addAttribute("member", member);
        return "member/member_detail";
    }

    @GetMapping("/modify")
    public String readMemberModify(@AuthenticationPrincipal Member getMember, Model model) throws Exception {
        Member member = memberService.readMember(getMember.getMemNo());
        model.addAttribute("member", member);
        return "member/member_modify";
    }

    @PostMapping("/modify")
    public String updateMemberModify(ReqMember reqMember)throws Exception{
        memberService.updateMember(reqMember);
        return "redirect:/member/myinfo";
    }
}
