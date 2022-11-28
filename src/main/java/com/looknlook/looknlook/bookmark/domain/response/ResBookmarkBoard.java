package com.looknlook.looknlook.bookmark.domain.response;

import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
public class ResBookmarkBoard {

    private Long bmNo;

    private String bmType;

    private Long no;


}
