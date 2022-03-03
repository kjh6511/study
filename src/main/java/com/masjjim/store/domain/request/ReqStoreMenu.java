package com.masjjim.store.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

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
