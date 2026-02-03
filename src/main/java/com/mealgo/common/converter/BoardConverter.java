package com.mealgo.common.converter;

import com.mealgo.common.code.BoardEnum;
import com.mealgo.common.code.CartEnum;
import com.mealgo.common.utils.CodeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class BoardConverter extends CodeConverter<BoardEnum> {
    public BoardConverter(){
        super(BoardEnum.class);
    }
}
