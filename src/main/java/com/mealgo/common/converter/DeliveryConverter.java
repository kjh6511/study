package com.mealgo.common.converter;

import com.mealgo.common.code.CartEnum;
import com.mealgo.common.code.DeliveryEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class DeliveryConverter extends CodeConverter<DeliveryEnum> {
    public DeliveryConverter(){
        super(DeliveryEnum.class);
    }
}
