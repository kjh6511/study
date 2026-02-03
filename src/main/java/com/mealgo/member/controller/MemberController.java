package com.mealgo.member.controller;

import com.mealgo.member.domain.dto.RequestMember;
import com.mealgo.member.domain.dto.ResponseMember;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/myinfo")
    public String readMember(@AuthenticationPrincipal Member auth, Model model) {
       Member member = memberService.readMember(auth.getMemNo());
        model.addAttribute(member);
        return "member/info";
    }

    @GetMapping("/list")
    public String readMemberList(Model model,
                                 @RequestParam(name= "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ResponseMember> membersPage = memberService.readMemberListForPage(pageable);
        membersPage.getContent().forEach(System.out::println);
        model.addAttribute("memberPage", membersPage);
        return "member/list";
    }
}
