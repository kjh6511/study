package com.looknlook.looknlook.common.codeEnum;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CodeStatus implements CodeValue{
    OK("01001", "사용"),
    WAIT("01002", "대기"),
    NO("01003","정지"),
    USER("02001","일반"),
    CEO("02002","사업자");

    private String code;
    private String value;

    CodeStatus(String code, String value){
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static CodeStatus getCodeStatusByCode(String code) {
        return Arrays.stream(CodeStatus.values())
                .filter(x-> x.code.equals(code))
                .findAny()
                .orElse(null);
    }

    public static String getValueByCode(String code) {
        return getCodeStatusByCode(code).getValue();
    }


}
