package com.looknlook.looknlook.order.controller;

import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.service.CartService;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.order.domain.request.ReqOrder;
import com.looknlook.looknlook.order.domain.response.ResOrder;
import com.looknlook.looknlook.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @GetMapping("")
    public String readOrderWrite(@RequestBody Long[] cartNoList, Model model)throws Exception{
        List<ResCart> cartList = cartService.readCartSelectList(cartNoList);
        model.addAttribute("cartList",cartList);
        return "";
    }

    @PostMapping("")
    public String createOrder(@RequestBody ReqOrder reqOrder, @AuthenticationPrincipal Member getMember) throws Exception {
        orderService.createOrder(reqOrder,getMember.getMemNo());
        return "완료page or 오류page";
    }

    @GetMapping("/{orderNo}")
    public String readOrder(@PathVariable("orderNo") Long orderNo, Model model){
        ResOrder resOrder = orderService.readOrder(orderNo);
        model.addAttribute("order",resOrder);
        return "상세";
    }
}
