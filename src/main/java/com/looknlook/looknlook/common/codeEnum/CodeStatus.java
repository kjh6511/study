package com.looknlook.looknlook.common.codeEnum;

public enum CodeStatus implements CodeValue{
    OK("01001", "사용"),
    WAIT("01002", "대기"),
    NO("01003","정지");

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
}
