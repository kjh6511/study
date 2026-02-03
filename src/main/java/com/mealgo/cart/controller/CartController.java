package com.mealgo.cart.controller;

import com.mealgo.cart.domain.dto.CartQuantityRequest;
import com.mealgo.cart.domain.dto.RequestCart;
import com.mealgo.cart.service.CartService;
import com.mealgo.common.code.CartEnum;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;

    @PostMapping("/add")
    public String addCart(@AuthenticationPrincipal Member auth, @ModelAttribute RequestCart requestCart) {
        requestCart.setMemNo(auth.getMemNo());
        cartService.addCart(requestCart);
        return "redirect:/carts";
    }

    @GetMapping
    public String cartList(@AuthenticationPrincipal Member auth, Model model) {
        model.addAttribute("cartList", cartService.getCartListByMemNo(auth.getMemNo()));
        return "cart/cart";
    }

    @PostMapping("/updateQuantity")
    @ResponseBody
    public Map<String, Object> updateQuantity(@RequestBody CartQuantityRequest cartQuantityRequest) {
        cartService.updateCartQuantity(cartQuantityRequest.getCartNo(), cartQuantityRequest.getDelta());
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("cartNo", cartQuantityRequest.getCartNo());
        return result;
    }

    @PostMapping("/delete")
    public String deleteMeal(@ModelAttribute RequestCart requestCart){
        cartService.updateCartState(requestCart.getCartNo(), CartEnum.DELETE);
        return "redirect:/carts";
    }
}
