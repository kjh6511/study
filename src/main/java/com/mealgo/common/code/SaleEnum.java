package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum SaleEnum implements CodeValue{
    CUPON("09001", "쿠폰"),
    EVENT("09002", "이벤트");

    private final String code;
    private final String value;

    SaleEnum(String code, String value) {
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
    public static SaleEnum from(String code) {
        return EnumCodeUtils.fromCode(SaleEnum.class, code);
    }
}
