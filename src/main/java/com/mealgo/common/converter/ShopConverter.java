package com.mealgo.common.converter;

import com.mealgo.common.code.MemberEnum;
import com.mealgo.common.code.ShopEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ShopConverter extends CodeConverter<ShopEnum> {
    public ShopConverter(){
        super(ShopEnum.class);
    }
}
