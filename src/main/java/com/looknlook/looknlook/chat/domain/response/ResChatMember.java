package com.looknlook.looknlook.chat.domain.response;

import com.looknlook.looknlook.chat.domain.entity.ChatMember;
import com.looknlook.looknlook.chat.domain.entity.Room;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class ResChatMember {

    private Long cmNo;

    private String cmStu;

    private LocalDateTime cmRegDt;

    private ResMember member;

    public ResChatMember(ChatMember chatMember){
        this.cmNo = chatMember.getCmNo();
        this.cmStu = chatMember.getCmStu();
        this.cmRegDt = chatMember.getCmRegDt();
        this.member = new ResMember(chatMember.getMember());
    }

}
