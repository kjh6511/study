package com.looknlook.looknlook.Item.domain.response;

import com.looknlook.looknlook.Item.domain.entity.ItemCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
public class ResItemCategoryMenuList {

    private Long icNum;

    private String icName;

    private List<ResItemCategoryMenuList> children;

    public ResItemCategoryMenuList(final ItemCategory itemCategory){
        this.icNum = itemCategory.getIcNum();
        this.icName = itemCategory.getIcName();
        this.children = itemCategory.getChildren()
                .stream()
                .map(ResItemCategoryMenuList::new)
                .collect(Collectors.toList());
    }
}

