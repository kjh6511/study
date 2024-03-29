package com.looknlook.looknlook.board.controller;

import com.looknlook.looknlook.board.service.BoardService;
import com.looknlook.looknlook.cart.domain.request.ReqCart;
import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.service.CartService;
import com.looknlook.looknlook.member.domain.entity.Member;
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
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String readCartList(@AuthenticationPrincipal Member getMember, Model model)throws Exception{

        return "/cart/list";
    }

}
