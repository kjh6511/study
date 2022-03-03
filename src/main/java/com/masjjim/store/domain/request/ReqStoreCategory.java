package com.masjjim.store.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class ReqStoreCategory {

    private Integer stoNo;
    private String stoCatName;
    private Integer stoCatNum;
    private List<ReqStoreMenu> reqStoreMenuList;
}
