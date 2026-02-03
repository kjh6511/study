package com.mealgo.common.converter;

import com.mealgo.common.code.CategoryEnum;
import com.mealgo.common.code.ShopEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class CategoryConverter extends CodeConverter<CategoryEnum> {
    public CategoryConverter(){
        super(CategoryEnum.class);
    }
}
