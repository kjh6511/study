package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum CartEnum implements CodeValue{
    ADD("05001", "담기"),
    BUY("05002", "구매"),
    DELETE("05003", "삭제");

    private final String code;
    private final String value;

    CartEnum(String code, String value) {
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
    public static CartEnum from(String code) {
        return EnumCodeUtils.fromCode(CartEnum.class, code);
    }
}
