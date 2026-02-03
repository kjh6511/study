package com.mealgo.board.domain.entity;

import com.mealgo.common.code.BoardEnum;
import com.mealgo.common.code.BoardTypeEnum;
import com.mealgo.common.converter.BoardConverter;
import com.mealgo.common.converter.BoardTypeConverter;
import com.mealgo.member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer brdNo;

    @Convert(converter = BoardTypeConverter.class)
    private BoardTypeEnum brdType;

    @Convert(converter = BoardConverter.class)
    private BoardEnum brdStat;

    @Column(name = "brdTitle", length = 200, nullable = false)
    private String brdTitle;

    @Lob
    @Column(name = "brdContent", nullable = false)
    private String brdContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memNo", nullable = false)
    private Member member;

    @Column(name = "brdRegDt", updatable = false)
    private LocalDateTime brdRegDt;

    @Column(name = "brdUpDt")
    private LocalDateTime brdUpDt;

    @PrePersist
    protected void onCreate() {
        this.brdRegDt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.brdUpDt = LocalDateTime.now();
    }
}

