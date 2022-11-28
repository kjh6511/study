package com.looknlook.looknlook.order.domain.response;

import com.looknlook.looknlook.order.domain.entity.Orders;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
public class ResOrder {

    private Long orderNo;

    private LocalDateTime orderDt;

    private String orderStu;

    private LocalDateTime orderArrDt;

    private String orderPay;

    private String orderAddr;

    private String orderName;

    private String orderPhone;

    private String orderAddrNum;

    private int payMoney;

    private List<ResOrderStock> resOrderStockList;

    public ResOrder(Orders orders) {
        this.orderNo = orders.getOrderNo();
        this.orderDt = orders.getOrderDt();
        this.orderStu = orders.getOrderStu();
        this.orderArrDt = orders.getOrderArrDt();
        this.orderPay = orders.getOrderPay();
        this.orderAddr = orders.getOrderAddr();
        this.orderName = orders.getOrderName();
        this.orderPhone = orders.getOrderPhone();
        this.orderAddrNum = orders.getOrderAddrNum();
        this.payMoney = orders.getPayMoney();
        this.resOrderStockList = orders.getOrderStocks()
                                        .stream()
                                        .map(ResOrderStock::new)
                                        .collect(Collectors.toList());
    }

}
