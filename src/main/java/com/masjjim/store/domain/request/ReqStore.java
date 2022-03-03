package com.masjjim.store.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReqStore {

    private String stoName;
    private Integer stoNum;
    private String stoCell;
    private String stoAddr1;
    private String stoAddr2;
    private String stoInfo;
    private String stoType;

}
