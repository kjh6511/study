package com.masjjim.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreMenu {

    private Integer stoCatNo;
    private Integer stoMenuNum;
    private String stoMenuName;
    private String stoMenuInfo;
    private int stoMenuPrice;
    private String stoMenuStat;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime stoMenuRegDt;
}
