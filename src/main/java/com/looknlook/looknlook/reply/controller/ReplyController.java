package com.looknlook.looknlook.reply.controller;

import com.looknlook.looknlook.cart.domain.response.ResCart;
import com.looknlook.looknlook.cart.service.CartService;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.order.domain.request.ReqOrder;
import com.looknlook.looknlook.order.domain.response.ResOrder;
import com.looknlook.looknlook.order.service.OrderService;
import com.looknlook.looknlook.reply.domain.request.ReqReply;
import com.looknlook.looknlook.reply.service.ReplyService;
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
@RequestMapping("/reply")
public class ReplyController {

    private ReplyService replyService;

    @PostMapping("")
    public String createReply(@RequestBody ReqReply reqReply,@AuthenticationPrincipal Member getMember)throws Exception{
        replyService.createReply(getMember.getMemNo(), reqReply);
        return "";
    }


}
