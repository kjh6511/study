package com.looknlook.looknlook.reply.service;

import com.looknlook.looknlook.reply.domain.request.ReqReply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

    @Test
    void createReply() {
        ReqReply reqReply = new ReqReply();
        reqReply.setReplyText("후기테스트02");
        reqReply.setReplyType("06001");
        reqReply.setItemNo(4L);
        reqReply.setOrderStockNo(4L);
        replyService.createReply(6L,reqReply);
    }

    @Test
    void readReplyAllByItemNo() {
        System.out.println(replyService.readReplyAllByItemNo(4L));
    }

    @Test
    void readReplyAllByMemNo() {
        System.out.println(replyService.readReplyAllByMemNo(6L));
    }
}