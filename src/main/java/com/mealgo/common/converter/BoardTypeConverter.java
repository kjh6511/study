package com.mealgo.common.converter;

import com.mealgo.common.code.BoardEnum;
import com.mealgo.common.code.BoardTypeEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class BoardTypeConverter extends CodeConverter<BoardTypeEnum> {
    public BoardTypeConverter(){
        super(BoardTypeEnum.class);
    }
}
