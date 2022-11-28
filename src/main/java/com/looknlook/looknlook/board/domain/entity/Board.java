package com.looknlook.looknlook.board.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.cart.domain.entity.Cart;
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
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    private String boardNm;

    private String boardStu;

    private String boardType;

    private String boardText;

    private LocalDateTime boardDt;

    private LocalDateTime boardUpDt;

    private int boardView;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_no")
    private Member member;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<BoardItem> boardItems;

}
