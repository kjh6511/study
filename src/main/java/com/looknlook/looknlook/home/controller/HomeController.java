package com.looknlook.looknlook.home.controller;

import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal Member member) {
        System.out.println("name :::::::: "+member.getMemName());
        model.addAttribute("memName",member.getMemName());
        model.addAttribute("menu", "home");
        return "home/home";
    }
}
