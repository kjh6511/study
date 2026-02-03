package com.mealgo.common.entity;

import com.mealgo.common.code.ShopEnum;
import com.mealgo.common.converter.ShopConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "code")
public class Code {
    @Id
    private String cdNo;

    private String cdNm;

    private String cdTy;

}
