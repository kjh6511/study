package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum OrderEnum implements CodeValue{
    WAIT("06001", "구매대기"),
    CONFIRM("06002", "구매확인"),
    CANCEL_REQUEST("06003", "취소요청"),
    CANCEL_DONE("06004", "취소완료"),
    CANCEL_DENY("06005", "취소불가");

    private final String code;
    private final String value;

    OrderEnum(String code, String value) {
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
    public static OrderEnum from(String code) {
        return EnumCodeUtils.fromCode(OrderEnum.class, code);
    }
}
