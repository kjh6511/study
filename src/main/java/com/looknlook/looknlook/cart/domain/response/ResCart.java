package com.looknlook.looknlook.cart.domain.response;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.response.ResItemCategory;
import com.looknlook.looknlook.Item.domain.response.ResStock;
import com.looknlook.looknlook.cart.domain.entity.Cart;
import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResCart {

    private Long stockNo;

    private String stockNm;

    private String stockStu;

    private String stockSize;

    private int stockQua;

    private String itemNm;

    private String itemStu;

    private Integer itemAmt;

    @QueryProjection
    public ResCart(Cart cart) {
        this.stockNo = cart.getStock().getStockNo();
        this.stockNm = cart.getStock().getStockNm();
        this.stockStu = cart.getStock().getStockStu();
        this.stockSize = cart.getStock().getStockSize();
        this.stockQua = cart.getStockQua();
        this.itemNm = cart.getStock().getItem().getItemNm();
        this.itemStu = cart.getStock().getItem().getItemStu();
        this.itemAmt = cart.getStock().getItem().getItemAmt();
    }
}
