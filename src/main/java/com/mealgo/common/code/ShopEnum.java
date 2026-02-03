package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum ShopEnum implements CodeValue {
    OPEN("02001", "사용"),
    CLOSED("02002","미사용"),
    SUSPENDED("02003","삭제");

    private final String code;
    private final String value;

    ShopEnum(String code, String value){
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
    public static ShopEnum from(String code) {
        return EnumCodeUtils.fromCode(ShopEnum.class, code);
    }

}

