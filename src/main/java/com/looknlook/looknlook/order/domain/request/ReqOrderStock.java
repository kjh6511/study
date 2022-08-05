package com.looknlook.looknlook.order.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ReqOrderStock {

    private Long stockNo;

    private int orderStockQua;


}
