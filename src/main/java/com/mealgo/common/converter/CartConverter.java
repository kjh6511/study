package com.mealgo.common.converter;

import com.mealgo.common.code.CartEnum;
import com.mealgo.common.code.MealEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class CartConverter extends CodeConverter<CartEnum> {
    public CartConverter(){
        super(CartEnum.class);
    }
}
