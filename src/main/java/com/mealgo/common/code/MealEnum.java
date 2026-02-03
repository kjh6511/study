package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum MealEnum implements CodeValue{
    ACTIVE("04001", "판매중"),
    INACTIVE("04002", "판매중지"),
    SOLDOUT("04003", "품절");

    private final String code;
    private final String value;

    MealEnum(String code, String value) {
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
    public static MealEnum from(String code) {
        return EnumCodeUtils.fromCode(MealEnum.class, code);
    }
}
