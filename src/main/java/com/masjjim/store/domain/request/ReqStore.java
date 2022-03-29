package com.masjjim.store.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ReqStore {

    private String stoName;
    private Integer stoNum;
    private String stoCell;
    private String stoAddr;
    private String stoAddr1;
    private String stoAddr2;
    private String stoInfo;
    private String stoType;
    private Integer memNo;

    //분류 게시판
    private Integer borMenuNo;

}
