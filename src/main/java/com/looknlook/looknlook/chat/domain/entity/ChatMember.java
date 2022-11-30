package com.looknlook.looknlook.chat.domain.entity;

import com.looknlook.looknlook.board.domain.entity.BoardItem;
import com.looknlook.looknlook.member.domain.entity.Member;
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
@Table(name = "chat_member")
public class ChatMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmNo;

    private String cmStu;

    private LocalDateTime cmRegDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_no")
    private Room room;

}
