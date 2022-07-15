package com.looknlook.looknlook.member.controller;

import com.looknlook.looknlook.member.domain.RequestDto.ReqLogin;
import com.looknlook.looknlook.member.domain.RequestDto.ReqMember;
import com.looknlook.looknlook.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", "로그인 정보를 확인해주세요.");
        return "member/login";
    }

    @GetMapping("/register")
    public String registerPage() {
/*        ModelAndView modelAndView = new ModelAndView("member/register");
        modelAndView.addObject("reqMember", new ReqMember());*/
        return "member/register";
    }

    @PostMapping("/register")
    public String createRegister(ReqMember reqMember) {
        memberService.createMember(reqMember);
        return "redirect:/";
    }

    // 추가
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}
