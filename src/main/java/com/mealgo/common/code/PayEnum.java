package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum PayEnum implements CodeValue{
    BANK("08001", "계좌"),
    CARD("08002", "카드");

    private final String code;
    private final String value;

    PayEnum(String code, String value) {
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
    public static PayEnum from(String code) {
        return EnumCodeUtils.fromCode(PayEnum.class, code);
    }
}
