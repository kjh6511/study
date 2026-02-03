package com.mealgo.wish.domain.entity;

import com.mealgo.meal.domain.entity.Meal;
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
@Table(name = "wish")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memNo")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mealNo")
    private Meal meal;

    private LocalDateTime wishRegDt;

    @PrePersist
    public void prePersist() {
        this.wishRegDt = LocalDateTime.now();
    }
}

