package com.mealgo.orders.controller;

import com.mealgo.cart.domain.dto.ResponseCart;
import com.mealgo.cart.service.CartService;
import com.mealgo.common.code.PayEnum;
import com.mealgo.member.domain.entity.Member;
import com.mealgo.orders.domain.dto.RequestOrder;
import com.mealgo.orders.domain.dto.ResponseOrder;
import com.mealgo.orders.domain.dto.SearchOrderCondition;
import com.mealgo.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;
    private final CartService cartService;

    @GetMapping("/form")
    public String orderForm(@RequestParam("cartNos") List<Integer> cartNos, Model model) {
        List<ResponseCart> cartList = cartService.findByCartNoIn(cartNos);
        int totalAmount = cartList.stream()
                .mapToInt(c -> c.getMeal().getMealPri() * c.getCartCnt())
                .sum();
        int shippingFee = totalAmount >= 30000 ? 0 : 3000;

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("shippingFee", shippingFee);
        model.addAttribute("payEnumList", PayEnum.values());
        return "order/orderForm";
    }



    @PostMapping("/create")
    public String createOrder(@AuthenticationPrincipal Member auth,
                              @ModelAttribute  RequestOrder requestOrder,
                              RedirectAttributes redirectAttributes) {
        Integer orderNo = ordersService.createOrder(auth.getMemNo(), requestOrder);
        redirectAttributes.addAttribute("orderNo", orderNo);
        return "redirect:/orders/complete";
    }

    @GetMapping("/complete")
    public String orderComplete(@RequestParam("orderNo") Integer orderNo, Model model) {
        ResponseOrder order = ordersService.findById(orderNo);
        model.addAttribute("order", order);
        return "order/orderComplete";
    }

    @GetMapping
    public String orderList(@AuthenticationPrincipal Member auth,
                            @ModelAttribute SearchOrderCondition condition,
                            @PageableDefault(size = 10) Pageable pageable,
                            Model model) {
        condition.setMemNo(auth.getMemNo());
        Page<ResponseOrder> orderPage = ordersService.readOrderList(condition, pageable);
        model.addAttribute("orderPage", orderPage);
        model.addAttribute("condition", condition);
        return "order/order";
    }

}
