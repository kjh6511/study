package com.mealgo.category.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mealgo.common.code.CategoryEnum;
import com.mealgo.common.converter.CategoryConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cateNo;

    @Column(length = 45, nullable = false)
    private String cateNm;

    @Column(nullable = false)
    private Integer cateLv;

    @Convert(converter = CategoryConverter.class)
    private CategoryEnum cateStat;

    private LocalDateTime cateRegDt;

    private LocalDateTime cateUpDt;

    //Self Join (ManyToOne - 상위 카테고리)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cateTopNo") // cateTopNo를 FK로 사용
    private Category parent;

    //Self Join (OneToMany - 하위 카테고리 목록)
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Category> children = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        this.cateRegDt = this.cateRegDt == null ? LocalDateTime.now() : this.cateRegDt;
        this.cateUpDt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.cateUpDt = LocalDateTime.now();
    }
}
