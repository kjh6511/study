package com.looknlook.looknlook.reply.domain.response;

import com.looknlook.looknlook.Item.domain.response.ResItem;
import com.looknlook.looknlook.Item.domain.response.ResStock;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.reply.domain.entity.Reply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter@Setter
public class ResReply {

    private Long replyNo;

    private String replyStu;

    private String replyType;

    private String replyText;

    private LocalDateTime replyDt;

    private ResMember member;

    private ResItem item;

    private ResStock stock;

    public ResReply(Reply reply){
        this.replyNo = reply.getReplyNo();
        this.replyStu = reply.getReplyStu();
        this.replyType = reply.getReplyType();
        this.replyText = reply.getReplyText();
        this.replyDt = reply.getReplyDt();
        this.member = new ResMember(reply.getMember());
        this.item = new ResItem(reply.getItem());
        this.stock = new ResStock(reply.getOrderStock().getStock());
    }

}
