package com.looknlook.looknlook.shop.domain.RequestDto;

import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class ReqShop {

    private String shopNm;

    private String shopImg;

    private String shopInfo;

}
