package com.looknlook.looknlook.Item.domain.response;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.Item.domain.request.ReqStock;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter @Setter
public class ResItemDetail {

    private Long shopNo;

    private String shopNm;

    private Long memNo;

    private ResItemCategory itemCategory;

    private String itemNm;

    private String itemStu;

    private String itemInfo;

    private Integer itemAmt;

    private List<ResStock> stocks;

    public ResItemDetail(Item item) {
        this.shopNo = item.getShop().getShopNo();
        this.shopNm = item.getShop().getShopNm();
        this.memNo = item.getShop().getMember().getMemNo();
        this.itemCategory = new ResItemCategory(item.getItemCategory());
        this.itemNm = item.getItemNm();
        this.itemStu = item.getItemStu();
        this.itemInfo = item.getItemInfo();
        this.itemAmt = item.getItemAmt();
        this.stocks = item.getStocks()
                            .stream().map(ResStock::new)
                            .collect(Collectors.toList());
    }
}
