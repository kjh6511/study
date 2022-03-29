package com.masjjim.store.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class ReqStoreMenu {

    private Integer stoCatNo;
    private Integer stoMenuNum;
    private String stoMenuName;
    private String stoMenuInfo;
    private int stoMenuPrice;

}
