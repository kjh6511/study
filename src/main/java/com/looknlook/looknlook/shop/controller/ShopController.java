package com.looknlook.looknlook.shop.controller;

import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/shop")
public class ShopController {

    @GetMapping("/{icNum}")
    public String readshop(@PathVariable("icNum") Long icNum, Model model) {
        model.addAttribute("menu", "shop");
        model.addAttribute("subMenu", icNum);
        model.addAttribute("icNum",icNum);
        System.out.println(icNum);
        return "shop/shop";
    }
}
