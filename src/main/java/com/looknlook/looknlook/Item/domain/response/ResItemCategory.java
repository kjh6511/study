package com.looknlook.looknlook.Item.domain.response;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResItemCategory {

    private Long icNum;

    private String icName;

    public ResItemCategory(ItemCategory itemCategory) {
        this.icNum = itemCategory.getIcNum();
        this.icName = itemCategory.getIcName();
    }

    public ResItemCategory(){}
}
