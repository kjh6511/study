package com.masjjim.member.controller;

import com.masjjim.member.domain.request.ReqRegister;
import com.masjjim.member.service.MemberService;
import com.masjjim.util.network.Header;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/check/{memId}")
    public Header<String> chekcMemberId(@PathVariable("memId") String memId) throws Exception {
        String result = memberService.checkMemberId(memId);
        return Header.DATA(result);
    }

    @PostMapping("/register")
    public Header<Void> registerMember(@RequestBody ReqRegister reqRegister) throws Exception {
        memberService.createMember(reqRegister);
        return Header.CREATE();
    }
}
