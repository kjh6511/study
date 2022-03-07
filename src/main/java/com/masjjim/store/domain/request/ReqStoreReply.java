package com.masjjim.store.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReqStoreReply {

    private Integer stoNo;
    private Integer reStoRlyNo;
    private String stoRlyInfo;

}
