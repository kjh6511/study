package com.mealgo.member.controller;

import com.mealgo.member.domain.dto.RequestMember;
import com.mealgo.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
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
       ModelAndView modelAndView = new ModelAndView("member/register");
        return "member/register";
    }

    @PostMapping("/register")
    public String createRegister(RequestMember requestMember) {
        memberService.createMember(requestMember);
        return "redirect:/";
    }

}
