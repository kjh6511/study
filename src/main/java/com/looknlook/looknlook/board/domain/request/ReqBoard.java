package com.looknlook.looknlook.board.domain.request;

import com.looknlook.looknlook.board.domain.entity.BoardItem;
import com.looknlook.looknlook.member.domain.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
public class ReqBoard {

    private String boardNm;

    private String boardType;

    private String boardText;

    private Long[] itemNoList;

}
