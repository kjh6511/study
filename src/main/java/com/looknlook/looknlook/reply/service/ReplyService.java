package com.looknlook.looknlook.reply.service;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.repository.ItemRepository;
import com.looknlook.looknlook.Item.repository.StockRepository;
import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.cart.repository.CartRepository;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.member.repository.MemberRepository;
import com.looknlook.looknlook.order.domain.entity.OrderStock;
import com.looknlook.looknlook.order.domain.entity.Orders;
import com.looknlook.looknlook.order.domain.request.ReqOrder;
import com.looknlook.looknlook.order.domain.response.ResOrder;
import com.looknlook.looknlook.order.repository.OrderRepository;
import com.looknlook.looknlook.order.repository.OrderStockRepository;
import com.looknlook.looknlook.reply.domain.entity.Reply;
import com.looknlook.looknlook.reply.domain.request.ReqReply;
import com.looknlook.looknlook.reply.domain.response.ResReply;
import com.looknlook.looknlook.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderStockRepository orderStockRepository;

    public void createReply(Long memNo, ReqReply reqReply) {
        Member member = memberRepository.findById(memNo)
                .orElseThrow(NullPointerException::new);
        Item item = itemRepository.findById(reqReply.getItemNo())
                .orElseThrow(NullPointerException::new);
        OrderStock orderStock = orderStockRepository.findById(reqReply.getOrderStockNo())
                .orElseThrow(NullPointerException::new);
        Reply reply = Reply.builder()
                .replyStu("01001")
                .replyType(reqReply.getReplyType())
                .replyText(reqReply.getReplyText())
                .member(member)
                .item(item)
                .orderStock(orderStock)
                .build();
        replyRepository.save(reply);
    }

    @Transactional
    public List<ResReply> readReplyAllByItemNo(Long itemNo) {
        List<Reply> replies = replyRepository.findAllByItemNo(itemNo);
        return replies.stream()
                .map(ResReply::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ResReply> readReplyAllByMemNo(Long memNo) {
        List<Reply> replies = replyRepository.findAllByMemNo(memNo);
        return replies.stream()
                .map(ResReply::new)
                .collect(Collectors.toList());
    }
}
