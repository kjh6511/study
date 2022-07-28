package com.looknlook.looknlook.Item.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "item_category")
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long icNum;

    private String icName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ic_top_num")
    private ItemCategory parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<ItemCategory> children = new ArrayList<ItemCategory>();

}
