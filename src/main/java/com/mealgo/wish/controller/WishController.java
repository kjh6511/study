package com.mealgo.wish.controller;

import com.mealgo.member.domain.entity.Member;
import com.mealgo.wish.domain.dto.ResponseWish;
import com.mealgo.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/wishes")
@Controller
public class WishController {

    private final WishService wishService;

    @GetMapping
    public String readWishList(@AuthenticationPrincipal Member auth,
                              @PageableDefault(size = 10, sort = "wishNo", direction = Sort.Direction.DESC) Pageable pageable,
                              Model model) {

        Page<ResponseWish> wishPage = wishService.getWishListByMemNo(auth.getMemNo(), pageable);
        model.addAttribute("wishList", wishPage);
        return "wish/wish";
    }

    @PostMapping("/add")
    public String addWish(@AuthenticationPrincipal Member member, @RequestParam("mealNo") Integer mealNo) {
        wishService.addWish(member.getMemNo(), mealNo);
        return "redirect:/meals";
    }

    @PostMapping("/delete")
    public String deleteWish(@AuthenticationPrincipal Member member, @RequestParam("wishNo") Integer wishNo) {
        wishService.deleteWish(wishNo);
        return "redirect:/wishes";
    }
}

