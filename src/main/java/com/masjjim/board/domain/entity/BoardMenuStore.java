package com.masjjim.board.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardMenuStore {

    private Integer borMenuNo;
    private Integer stoNo;
}
