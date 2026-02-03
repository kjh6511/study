package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum CategoryEnum implements CodeValue {
    USE("03001", "사용"),
    DISABLE("03002","미사용"),
    DELETE("03003","삭제");

    private final String code;
    private final String value;

    CategoryEnum(String code, String value){
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
    public static CategoryEnum from(String code) {
        return EnumCodeUtils.fromCode(CategoryEnum.class, code);
    }

}

