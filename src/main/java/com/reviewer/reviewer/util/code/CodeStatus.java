package com.reviewer.reviewer.util.code;

import java.util.Arrays;

public enum CodeStatus implements CodeValue{
    OK("01001", "사용"),
    WAIT("01002", "대기"),
    NO("01003","정지"),
    ADMIN("02001","관리자"),
    USER("02002","사용자");

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
