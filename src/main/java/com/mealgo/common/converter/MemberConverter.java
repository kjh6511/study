package com.mealgo.common.converter;

import com.mealgo.common.code.MemberEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class MemberConverter extends CodeConverter<MemberEnum> {
    public MemberConverter(){
        super(MemberEnum.class);
    }
}
