package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum BoardEnum implements CodeValue {
    ACTIVE("11001", "등록"),
    PRIVATE("11002", "비공개"),
    DELETED("11003", "삭제");

    private final String code;
    private final String value;

    BoardEnum(String code, String value){
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
    public static BoardEnum from(String code) {
        return EnumCodeUtils.fromCode(BoardEnum.class, code);
    }

}

