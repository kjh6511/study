package com.looknlook.looknlook.code.domain.entity;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder @ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "code")
public class Code {

    @Id
    private String cdNum;

    private String cdTy;

    private String cdNm;
}
