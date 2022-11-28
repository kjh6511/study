package com.looknlook.looknlook.Item.domain.response;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import com.looknlook.looknlook.shop.domain.ResponseDto.ResShop;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResItem {

    private Long itemNo;

    private String itemNm;

    private String itemStu;

    private String itemInfo;

    private Integer itemAmt;

    private ResItemCategory itemCategory;

    private ResShop shop;

    private List<ResStock> stocks;

    public ResItem(Item item) {
        this.itemNo = item.getItemNo();
        this.itemNm = item.getItemNm();
        this.itemStu = item.getItemStu();
        this.itemInfo = item.getItemInfo();
        this.itemAmt = item.getItemAmt();
        this.itemCategory =  new ResItemCategory(item.getItemCategory());
        this.stocks = item.getStocks()
                            .stream().map(ResStock::new)
                            .collect(Collectors.toList());
    }
}
