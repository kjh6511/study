package com.looknlook.looknlook.bookmark.domain.response;

import com.looknlook.looknlook.Item.domain.response.ResItemCategory;
import com.looknlook.looknlook.bookmark.domain.entity.Bookmark;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ResBookmarkItem {

    private Long bmNo;

    private String bmType;

    private Long itemNo;

    private String itemNm;

    private String itemStu;

    private Integer itemAmt;

    private Long shopNo;

    private String shopNm;

    private String shopStu;

    @QueryProjection
    public ResBookmarkItem(Bookmark bookmark){
        this.bmNo = bookmark.getBmNo();
        this.bmType = bookmark.getBmType();

    }
}
