package com.looknlook.looknlook.bookmark.domain.request;

import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
public class ReqBookmark {

    private String bmType;

    private Long boardNo;

    private Long itemNo;

    private Long shopNo;
}
