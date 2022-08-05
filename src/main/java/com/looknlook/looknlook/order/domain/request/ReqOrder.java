package com.looknlook.looknlook.order.domain.request;

import com.looknlook.looknlook.Item.domain.request.ReqStock;
import com.looknlook.looknlook.order.domain.entity.OrderStock;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter @Setter
public class ReqOrder {

    private String orderPay;

    private String orderAddr;

    private String orderName;

    private String orderPhone;

    private String orderAddrNum;

    private List<OrderStock> orderStocks;

}
