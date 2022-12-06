package com.looknlook.looknlook.chat.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private String roomId;

    private Long roomNo;
    private String writer;
    private String message;
    private Long memNo;
}
