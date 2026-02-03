package com.mealgo.common.converter;

import com.mealgo.common.code.PayEnum;
import com.mealgo.common.code.SaleEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class SaleConverter extends CodeConverter<SaleEnum> {
    public SaleConverter(){
        super(SaleEnum.class);
    }
}
