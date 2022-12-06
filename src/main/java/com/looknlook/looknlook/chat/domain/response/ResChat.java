package com.looknlook.looknlook.chat.domain.response;

import com.looknlook.looknlook.chat.domain.entity.Chat;
import com.looknlook.looknlook.chat.domain.entity.Room;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ResChat {

    private Long chatNo;

    private String chatCont;

    private LocalDateTime chatDt;

    private ResMember member;

    public ResChat(Chat chat){
        this.chatNo = chat.getChatNo();
        this.chatCont = chat.getChatCont();
        this.chatDt = chat.getChatDt();
        this.member = new ResMember(chat.getMember());
    }

}
