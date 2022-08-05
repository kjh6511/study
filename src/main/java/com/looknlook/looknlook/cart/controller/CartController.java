package com.looknlook.looknlook.cart.controller;

import com.looknlook.looknlook.cart.domain.request.ReqCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.service.CartService;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.order.domain.request.ReqOrderStock;
import com.looknlook.looknlook.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    public String readCartList(@AuthenticationPrincipal Member getMember, Model model)throws Exception{
        List<ResCart> cartList = cartService.readCartList(getMember.getMemNo());
        model.addAttribute("cartList", cartList);
        return "/cart/list";
    }

    @PostMapping("")
    public void createCart(@AuthenticationPrincipal Member getMember, @RequestBody ReqCart reqCart)throws Exception{
        cartService.createCart(getMember.getMemNo(),reqCart);
    }
}
