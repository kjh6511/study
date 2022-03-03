package com.masjjim.member.controller;

import com.masjjim.member.domain.request.ReqRegister;
import com.masjjim.member.domain.response.ResMember;
import com.masjjim.member.service.MemberService;
import com.masjjim.util.network.Header;
import com.masjjim.util.security.domain.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/check/{memId}")
    public Header<String> chekcMemberId(@PathVariable("memId") String memId) throws Exception {
        String result = memberService.checkMemberId(memId);
        return Header.DATA(result);
    }

    @PostMapping("/register")
    public Header<Void> registerMember(@RequestBody ReqRegister reqRegister) throws Exception {
        String password = reqRegister.getMemPw();
        reqRegister.setMemPw(passwordEncoder.encode(password));
        memberService.createMember(reqRegister);
        return Header.CREATE();
    }

    @PreAuthorize("hasAnyRole('MEMBER','ADMIN')")
    @GetMapping("/myinfo")
    public Header<ResMember> readMyinfo(@AuthenticationPrincipal CustomUser customUser) throws Exception {
        ResMember resMember = memberService.readMember(customUser.getMemNo());
        return Header.DATA(resMember);
    }

}
