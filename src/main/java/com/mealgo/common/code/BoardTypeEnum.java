package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum BoardTypeEnum implements CodeValue {
    PERSONAL("10001", "1:1문의"),
    QNA("10002", "문의사항"),
    GUIDE("10003", "설명서"),
    FREE("10004", "자유게시판");

    private final String code;
    private final String value;

    BoardTypeEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static BoardTypeEnum from(String code) {
        return EnumCodeUtils.fromCode(BoardTypeEnum.class, code);
    }

}

