package com.mealgo.common.converter;

import com.mealgo.common.code.OrderEnum;
import com.mealgo.common.code.PayEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class PayConverter extends CodeConverter<PayEnum> {
    public PayConverter(){
        super(PayEnum.class);
    }
}
