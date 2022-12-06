package com.looknlook.looknlook.chat.domain.entity;

import com.looknlook.looknlook.board.domain.entity.BoardItem;
import com.looknlook.looknlook.member.domain.entity.Member;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;

    private String roomNm;

    private String roomType;

    private String roomStu;

    private LocalDateTime roomRegDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_no")
    private Shop shop;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<ChatMember> chatMembers;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Chat> chats;
}
