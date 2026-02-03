package com.mealgo.common.converter;

import com.mealgo.common.code.OrderEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class OrderConverter extends CodeConverter<OrderEnum> {
    public OrderConverter(){
        super(OrderEnum.class);
    }
}
