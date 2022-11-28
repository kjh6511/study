package com.looknlook.looknlook.board.domain.response;

import com.looknlook.looknlook.board.domain.entity.BoardItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ResBoardItem {

    private Long itemNo;

    private String itemNm;

    private String itemStu;

    private Long shopNo;

    private String shopNm;

    private String shopStu;

    public ResBoardItem(BoardItem boardItem){
        this.itemNo = boardItem.getItem().getItemNo();
        this.itemNm = boardItem.getItem().getItemNm();
        this.itemStu = boardItem.getItem().getItemStu();
        this.shopNo = boardItem.getItem().getShop().getShopNo();
        this.shopNm = boardItem.getItem().getShop().getShopNm();
        this.shopStu = boardItem.getItem().getShop().getShopStu();
    }
}
