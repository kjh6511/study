package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum MemberEnum implements CodeValue {
    WAIT("01001", "대기"),
    OK("01002", "등록"),
    STOP("01003","정지"),
    DELETE("01004","삭제");

    private final String code;
    private final String value;

    MemberEnum(String code, String value){
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
    public static MemberEnum from(String code) {
        return EnumCodeUtils.fromCode(MemberEnum.class, code);
    }

}

