package com.looknlook.looknlook.reply.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class ReqReply {

    private String replyType;

    private String replyText;

    private Long itemNo;

    private Long orderStockNo;

}
