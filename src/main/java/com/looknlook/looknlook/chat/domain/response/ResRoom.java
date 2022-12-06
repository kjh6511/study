package com.looknlook.looknlook.chat.domain.response;

import com.looknlook.looknlook.chat.domain.entity.Chat;
import com.looknlook.looknlook.chat.domain.entity.ChatMember;
import com.looknlook.looknlook.chat.domain.entity.Room;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResRoom {

    private Long roomNo;

    private String roomNm;

    private String roomType;

    private String roomStu;

    private LocalDateTime roomRegDt;

    private List<ResChatMember> chatMembers;

    private List<ResChat> chats;

    @QueryProjection
    public ResRoom(Room room){
        this.roomNo = room.getRoomNo();
        this.roomNm = room.getRoomNm();
        this.roomType = room.getRoomType();
        this.roomStu = room.getRoomStu();
        this.roomRegDt = room.getRoomRegDt();
        this.chatMembers = room.getChatMembers()
                .stream().map(ResChatMember::new)
                .collect(Collectors.toList());
        this.chats = room.getChats()
                .stream().map(ResChat::new)
                .collect(Collectors.toList());
    }
}
