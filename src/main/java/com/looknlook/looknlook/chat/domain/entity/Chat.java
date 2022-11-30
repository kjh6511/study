package com.looknlook.looknlook.chat.domain.entity;

import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatNo;

    private String chatCont;

    private LocalDateTime chatDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_no")
    private Room room;

}
