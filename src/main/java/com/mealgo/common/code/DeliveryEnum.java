package com.mealgo.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mealgo.common.utils.EnumCodeUtils;

public enum DeliveryEnum implements CodeValue{
    CONFIRM("07001", "구매확인"),
    DELIVERY_READY("07002", "배송준비"),
    DELIVERY_ONGOING("07003", "배송중"),
    DELIVERY_COMPLETE("07004", "배송완료"),
    DONE("07005", "구매확인"),
    RETURN_REQUEST("07006", "반품요청");

    private final String code;
    private final String value;

    DeliveryEnum(String code, String value) {
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
    public static DeliveryEnum from(String code) {
        return EnumCodeUtils.fromCode(DeliveryEnum.class, code);
    }
}
