package com.looknlook.looknlook.Item.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter @Setter
public class ReqItem {

    private Long shopNo;

    private Long icNum;

    private String itemNm;

    private String itemStu;

    private String itemInfo;

    private Integer itemAmt;

    private List<ReqStock> stocks;

}
