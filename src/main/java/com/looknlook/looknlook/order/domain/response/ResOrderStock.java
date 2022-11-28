package com.looknlook.looknlook.order.domain.response;

import com.looknlook.looknlook.code.repository.CodeRepository;
import com.looknlook.looknlook.order.domain.entity.OrderStock;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter @Setter
public class ResOrderStock {

    private Long orderStockNo;

    private String orderStockStu;

    private LocalDateTime orderStockDt;

    private int orderStockQua;

    private String stockNm;

    private String stockSize;

    private Long itemNo;

    private String itemNm;

    private Integer itemAmt;

    public ResOrderStock(OrderStock orderStock){
        this.orderStockNo = orderStock.getOrderStockNo();
        this.orderStockStu = orderStock.getOrderStockStu();
        this.orderStockDt = orderStock.getOrderStockDt();
        this.orderStockQua = orderStock.getOrderStockQua();
        this.stockNm = orderStock.getStock().getStockNm();
        this.stockSize = orderStock.getStock().getStockSize();
        this.itemNo = orderStock.getStock().getItem().getItemNo();
        this.itemNm = orderStock.getStock().getItem().getItemNm();
        this.itemAmt = orderStock.getStock().getItem().getItemAmt();
    }
}
