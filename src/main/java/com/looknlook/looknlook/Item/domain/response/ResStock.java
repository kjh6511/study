package com.looknlook.looknlook.Item.domain.response;

import com.looknlook.looknlook.Item.domain.entity.Stock;
import lombok.Data;

@Data
public class ResStock {

    private Long stockNo;

    private String stockNm;

    private String stockStu;

    private String stockSize;

    private Integer stockQtq;

    private ResItem item;

    public ResStock(Stock stock) {
        this.stockNo = stock.getStockNo();
        this.stockNm = stock.getStockNm();
        this.stockStu = stock.getStockStu();
        this.stockSize = stock.getStockSize();
        this.stockQtq = stock.getStockQtq();
        //this.item = new ResItem(stock.getItem());
    }
}
