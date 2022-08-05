package com.looknlook.looknlook.Item.domain.response;

import com.looknlook.looknlook.Item.domain.entity.Item;
import com.looknlook.looknlook.member.domain.ResponseDto.ResMember;
import com.looknlook.looknlook.shop.domain.entity.Shop;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class ResItemWithShop {

    private Long itemNo;

    private String itemNm;

    private String itemStu;

    private Integer itemAmt;

    private Long shopNo;

    private String shopNm;

    private Long icNum;

    private String icName;

    @QueryProjection
    public ResItemWithShop(Item item) {
        this.itemNo = item.getItemNo();
        this.itemNm = item.getItemNm();
        this.itemStu = item.getItemStu();
        this.itemAmt = item.getItemAmt();
        this.shopNo = item.getShop().getShopNo();
        this.shopNm = item.getShop().getShopNm();
        this.icNum = item.getItemCategory().getIcNum();
        this.icName = item.getItemCategory().getIcName();
    }
}
